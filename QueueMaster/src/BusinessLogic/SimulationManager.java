package BusinessLogic;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import GUI.First;
import Model.Client;
import Model.QueueClass;

public class SimulationManager implements Runnable {

	public int timeLimit;
	public int maxProcessingTime;
	public int minProcessingTime;
	public int numberOfQueues;
	public int numberOfClients;
	public int minArrival;
	public int maxArrival;
	private Scheduler scheduler;
	private List<Client> generatedClient;
	private First page;

	public SimulationManager(int tl, int maxP, int minP, int nrQ, int nrC, int minA, int maxA, First page) {
		this.timeLimit = tl;
		this.maxProcessingTime = maxP;
		this.minProcessingTime = minP;
		this.numberOfQueues = nrQ;
		this.numberOfClients = nrC;
		this.minArrival = minA;
		this.maxArrival = maxA;
		this.page = page;
		this.scheduler = new Scheduler(numberOfQueues, 100);
		this.generatedClient = new ArrayList<>();
		generateNRandomClients();

	}

	private void generateNRandomClients() {
		Random rand = new Random();
		for (int i = 1; i <= numberOfClients; i++) {
			int arrivalTime = 0;
			int serviceTime = 0;
			try {
				arrivalTime = rand.nextInt(minArrival, maxArrival);
				serviceTime = rand.nextInt(minProcessingTime, maxProcessingTime);
			} catch (IllegalArgumentException iae) {
				page.showStringError("The maximum values ​​must be higher than the minimum values!");
				System.exit(-1);
			}
			Client client = new Client(i, arrivalTime, serviceTime);
			this.generatedClient.add(client);
		}

		Collections.sort(this.generatedClient, new ClientComparator());

	}

	public Scheduler getScheduler() {
		return scheduler;
	}

	public void setScheduler(Scheduler scheduler) {
		this.scheduler = scheduler;
	}

	public List<Client> getGeneratedClient() {
		return generatedClient;
	}

	public void setGeneratedClient(List<Client> generatedClient) {
		this.generatedClient = generatedClient;
	}

	@Override
	public void run() {
		float waitingTime = 0;
		float serviceTime = 0;
		int peakHour = 0;
		int maxim = 0;
		String allText = "";
		FileWriter writer = null;
		try {
			writer = new FileWriter("file.txt");
		} catch (IOException e) {
			System.out.println("A apărut o eroare: " + e.getMessage());
		}
		float sum = 0;
		for (Client c : this.generatedClient) {
			sum = sum + c.getServiceTime();

		}

		serviceTime = sum / this.generatedClient.size();
		try {
			writer.write("Clients: " + this.generatedClient.toString() + "\n");
			writer.flush();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		int currentTime = 0;
		while (currentTime <= this.timeLimit) {
			allText = "Clients: " + this.generatedClient.toString() + "\n";
			float sum2 = 0;
			for (QueueClass q : this.scheduler.getQueues()) {
				sum2 = sum2 + q.getWaitingPeriod().get();
			}
			int index = 0;
			for (Client c : this.generatedClient) {
				if (c.getArrivalTime() == currentTime) {
					this.scheduler.dispatchClient(c);
					index = c.getId();

				}
			}
			for (int i = 0; i < this.generatedClient.size(); i++) {
				if (this.generatedClient.get(i).getId() == index || this.generatedClient.get(i).getServiceTime()==0) {
					this.generatedClient.remove(i);
					i--;
				}
			}

			String outputString = "Time:" + currentTime + "  " + this.scheduler.toString() + "\n";
			// allText = allText + outputString;
			try {
				writer.write(outputString);
				page.updateResultPane(allText + outputString);
				writer.flush();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			currentTime++;
			for (QueueClass q : this.scheduler.getQueues()) {
				if (q.getQueue().peek() != null) {
					q.getQueue().peek().setServiceTime(q.getQueue().peek().getServiceTime() - 1);
					q.getWaitingPeriod().set(q.getWaitingPeriod().get() - 1);
					if (q.getQueue().peek().getServiceTime() == 0) {
						q.getQueue().remove(q.getQueue().peek());
					}
				}
			}
			waitingTime = waitingTime + sum2;
			int variable = 0;
			for (QueueClass q : this.scheduler.getQueues()) {
				if (q.getQueue().isEmpty() == false)
					variable = variable + q.getQueue().size();
				else
					continue;
			}
			if (variable > maxim) {
				maxim = variable;
				peakHour = currentTime - 1;
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		try {
			writer.write("Average wainting Time: " + waitingTime / (currentTime - 1) + "\nAverage service Time: "
					+ serviceTime + "\nPeak Hour is at Time: " + peakHour);
			page.updateAvServPane("" + serviceTime);
			page.updateAvWaitPane("" + waitingTime);
			page.updatePeakHourPane("" + peakHour);
			writer.flush();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

class ClientComparator implements Comparator<Client> {
	public int compare(Client c1, Client c2) {
		return Integer.compare(c1.getArrivalTime(), c2.getArrivalTime());
	}
}