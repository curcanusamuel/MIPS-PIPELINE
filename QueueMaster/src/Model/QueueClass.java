package Model;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class QueueClass implements Runnable{

	
	private BlockingQueue<Client> queue;
	private AtomicInteger waitingPeriod;
	private int number=0;
	
	
	public QueueClass(int n,int nr)
	{
		number=nr;
		queue=new ArrayBlockingQueue<>(n);
		waitingPeriod= new AtomicInteger(0);
	}
	
	
	public void addClient(Client newClient)
	{
		queue.add(newClient);
		waitingPeriod.set(waitingPeriod.get()+newClient.getServiceTime());
	}
	public void removeClient(Client newClient)
	{
		queue.remove(newClient);
		waitingPeriod.set(waitingPeriod.get()-newClient.getServiceTime());
	}
	
	public int getNumber() {
		return number;
	}


	public void setNumber(int number) {
		this.number = number;
	}


	public BlockingQueue<Client> getQueue() {
		return queue;
	}

	public void setQueue(BlockingQueue<Client> queue) {
		this.queue = queue;
	}

	public AtomicInteger getWaitingPeriod() {
		return waitingPeriod;
	}

	public void setWaitingPeriod(AtomicInteger waitingPeriod) {
		this.waitingPeriod = waitingPeriod;
	}

	@Override
	public String toString() {
		return "\nQ"+number+":" + queue + ", waitingPeriod=" + waitingPeriod + "\n";
	}


	@Override
	public void run() {
		while(true)
		{
			this.queue.peek();
			try {
			    Thread.sleep(1000*queue.peek().getServiceTime()); // wait for one second
			} catch (InterruptedException e) {
			    e.printStackTrace();
			}
		}
		
	}
	
	
	
	
	
}
