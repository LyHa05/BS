package aufgabe02_threads;

import java.util.Random;

/**
 * @author Lucas Anders, Lydia Pflug
 * @date 07.10.2016
 * Die Klasse simuliert zufaellig einen Unfall nach einer bestimmten Zeit.
 */

public class Accident extends Thread {

	static boolean unfall = false;
	
	
	
	/**
	 * Methode gibt Wert von unfall zurueck.
	 * @return true, wenn ein Unfall vorhanden ist, ansonsten false
	 */
	public static boolean unfallVorhanden() {
		
		return unfall;
		
	}
	
	/**
	 * Methode generiert nach einer zufaelligen Zeit einen Unfall und
	 * setzt den Boolean false auf true.
	 * @throws InterruptedException
	 */
	public static void unfallGenerieren() throws InterruptedException {
		
		Random random = new Random();
		
		Thread.sleep(random.nextInt(10000-1) + 1);
		
		// TODO pruefen, wie Unfall nicht immer ausgefuehrt wird
		unfall = false;
		
	}
	
	
	
	
}
