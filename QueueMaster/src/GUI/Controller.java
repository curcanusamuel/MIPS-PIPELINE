package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import BusinessLogic.SimulationManager;

public class Controller {

	private First page;

	public Controller(First page) {
		this.page = page;
		page.runListener(new ActionListener() {
			String clients1 = "";
			String queues1 = "";
			String simTime1 = "";
			String minArr1 = "";
			String maxArr1 = "";
			String minServ1 = "";
			String maxServ1 = "";

			public void actionPerformed(ActionEvent e) throws IllegalArgumentException {
				clients1 = page.getClients();
				queues1 = page.getQueues();
				simTime1 = page.getSimulationTime();
				minArr1 = page.getMinArrTime();
				maxArr1 = page.getMaxArrTime();
				minServ1 = page.getMinServTime();
				maxServ1 = page.getMaxServTime();
				int clients = 0;
				int queues = 0;
				int simTime = 0;
				int minArr = 0;
				int maxArr = 0;
				int minServ = 0;
				int maxServ = 0;
				
				try {
				    clients = Integer.parseInt(clients1);
				    queues = Integer.parseInt(queues1);
				    simTime = Integer.parseInt(simTime1);
				    minArr = Integer.parseInt(minArr1);
				    maxArr = Integer.parseInt(maxArr1);
				    minServ = Integer.parseInt(minServ1);
				    maxServ = Integer.parseInt(maxServ1);
				} catch (NumberFormatException nfe) {
				    page.showStringError("Insert valid data!");
				}


				SimulationManager gen = new SimulationManager(simTime, maxServ, minServ, queues, clients, minArr,
						maxArr,page);
				Thread t=new Thread(gen);
				t.start();
				
			}
		}

		);
	}
}
