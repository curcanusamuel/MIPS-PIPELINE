package BusinessLogic;
import java.util.List;

import Model.Client;
import Model.QueueClass;

public class Strategy {

	public void addClient(List<QueueClass> queues, Client c) {
		int add = 0;
		int min = Integer.MAX_VALUE;
		for (QueueClass q : queues) {
			if (q.getQueue().isEmpty()) {
				if (add == 0) {
					q.addClient(c);
					add=1;
				} else {
					break;
				}

			}

		}
		if(add==0)
		{
			for (QueueClass q : queues)
			{
				if(q.getWaitingPeriod().get()<=min)
				{
					min=q.getWaitingPeriod().get();
				}
			}
			while(add==0)
			{
				for (QueueClass q : queues)
				{
					if(q.getWaitingPeriod().get()==min)
					{
						q.addClient(c);
						add=1;
						break;
					}
				}
			}
		}

	}
}