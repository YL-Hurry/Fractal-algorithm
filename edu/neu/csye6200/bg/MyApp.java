/**
 * 
 */
package edu.neu.csye6200.bg;

/**
 author name:YUE LIU
NU number:001353606
 */
public class MyApp {

	public void run() {
		MyFrame mf = new MyFrame();
		MyRunnable sc =  new MyRunnable();
		
		//Runnable calculate tree
		Thread scthread = new Thread(sc);
		scthread.start();
		
		//mfthread draw the plant
		Thread mfthread = new Thread(mf);	
		mfthread.start();
	}
	public static void main(String[] args) {
		MyApp ma = new MyApp();	
		ma.run();		
		System.out.println("MyAppUI is exiting");
	}

}
