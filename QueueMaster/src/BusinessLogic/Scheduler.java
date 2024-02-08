package BusinessLogic;
import java.util.ArrayList;
import java.util.List;

import Model.Client;
import Model.QueueClass;

public class Scheduler {

	
	private List<QueueClass> queues;
	public String toString() {
		return ""+ queues;
	}
	private int maxNoQueues;
	private int maxClientsPerQueue;
	private Strategy s;
	
	public Scheduler(int maxNoQueues,int maxClientsPerQueue)
	{
		this.queues=new ArrayList<>(maxNoQueues);
		
		this.maxNoQueues=maxNoQueues;
		this.maxClientsPerQueue=maxClientsPerQueue;
		this.s=new Strategy();
		for(int i=0;i<maxNoQueues;i++)
		{
			QueueClass Q = new QueueClass(maxClientsPerQueue,i+1);
			queues.add(Q);
			Thread t=new Thread(Q);
		}
	}
	
	
	public void dispatchClient(Client c)
	{
		this.s.addClient(queues, c);
	}
	public List<QueueClass> getQueues() {
		return queues;
	}
	public void setQueues(List<QueueClass> queues) {
		this.queues = queues;
	}
	public int getMaxNoQueues() {
		return maxNoQueues;
	}
	public void setMaxNoQueues(int maxNoqueues) {
		this.maxNoQueues = maxNoqueues;
	}
	public int getMaxClientsPerQueue() {
		return maxClientsPerQueue;
	}
	public void setMaxClientsPerQueue(int maxClientsPerQueue) {
		this.maxClientsPerQueue = maxClientsPerQueue;
	}
	public Strategy getStrategy() {
		return strategy;
	}
	public void setStrategy(Strategy strategy) {
		this.strategy = strategy;
	}
	public Strategy getS() {
		return s;
	}



	public void setS(Strategy s) {
		this.s = s;
	}
	private Strategy strategy;
}