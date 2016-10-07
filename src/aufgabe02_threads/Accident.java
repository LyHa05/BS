package aufgabe02_threads;

import java.util.Random;

/**
 * @author Lucas Anders, Lydia Pflug
 * @date 07.10.2016
 * Die Klasse simuliert zufaellig einen Unfall nach einer bestimmten Zeit.
 */

public class Accident extends Thread {

	boolean unfall = false;
	
	/**
	 * Methode gibt Wert von unfall zurueck.
	 * @return true, wenn ein Unfall vorhanden ist, ansonsten false
	 */
	public boolean unfallVorhanden() {
		
		return unfall;
		
	}
	
	/**
	 * Methode generiert nach einer zufaelligen Zahl einen Unfall und
	 * setzt den Boolean false auf true.
	 * @throws InterruptedException
	 */
	public void unfallGenerieren(int zahl) throws InterruptedException {
		
		if (zahl == 42) {
			unfall = true;
		}
		
	}
	
	@Override
	public void run() {
	
		boolean unterbrechen = true;
		
		while(unterbrechen && !unfall) {
			Random random = new Random();
			try {
				if(!isInterrupted()) {
					unfallGenerieren(random.nextInt((100000000 -1) + 1));
				} else {
					unterbrechen = false;
				}
			} catch (InterruptedException e) {
		
				e.printStackTrace();
			}
		}
	
	}
	
	
	
	
}
