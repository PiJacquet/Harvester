package Tool;

import java.util.ArrayList;

public class ThreadsPool {
	
	private ArrayList<Thread> pool;
	
	public void ThreadsPool(int size) {
		if(size<=0)
			throw new IllegalArgumentException();
		pool = new ArrayList<Thread>();
		for(int i=0;i<size;i++)
			pool.add(new Thread());
	}

	public Thread getThread() {
		if(pool.size()>0) {
			Thread t = pool.get(0);
			pool.remove(t);
			return t;
		}else {
			return null;
		}
			
	}
}
