package edu.neu.csye6200.bg;

/*author name:YUE LIU
NU number:001353606*/

public class MyRunnable implements Runnable  {
	private boolean done = false;
	BGRule bgr = new BGRule();
	Stem st = new Stem();
	// Constructor - with a name
	public MyRunnable() {
		
	}

	public void run() {
		
		do {
			synchronized (MyFrame.lock) {//add the lock to the thread
				try {
					//System.out.println("the simu is running");
					MyFrame.lock.notify();
					MyFrame.lock.wait();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				} 
				//wake up and create the child stem
				//System.out.println("runnablewake");
				st.getGen();
				switch (MyFrame.rule) {
					case 1:bgr.child1(Stem.root);break;
					case 2:bgr.child2(Stem.root);break;
				}
			}
		}while(!done);
	}


}
