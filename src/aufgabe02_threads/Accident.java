package aufgabe02_threads;

import java.util.Random;

public class Accident extends Thread {

	static boolean unfall = false;
	
	public static boolean unfallVorhanden() {
		
		return unfall;
		
	}
	
	public static void unfallGenerieren() throws InterruptedException {
		
		Random random = new Random();
		
		Thread.sleep(random.nextInt(10000-1) + 1);
		
		// TODO pruefen, wie Unfall nicht immer ausgefuehrt wird
		unfall = false;
		
	}
	
	
	
	
}
