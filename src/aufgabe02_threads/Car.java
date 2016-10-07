package aufgabe02_threads;

import java.util.Random;

/**
 * @author Lucas Anders, Lydia Pflug
 * @date 07.10.2016
 * Die Klasse modelliert ein Auto, das Runden in einem Rennen faehrt.
 */

public class Car extends Thread {
	
	private int startNummer;
	private int gesamtFahrzeit;
	private int anzahlZuFahrendeRunden;
	
	public Car(int nummer, int runden) {
		this.startNummer = nummer;
		this.anzahlZuFahrendeRunden = runden;
	}

	
	/** 
	 * Methode fuehrt entsprechend der Anzahl der zu fahrenden Runden
	 * die Methode rundeFahren() aus und setzt danach den boolean rennenBeendet
	 * auf true.
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		try {
			//Runden fahren
			int i = 1;
			while (i <= anzahlZuFahrendeRunden) {
				
				//Pruefen, ob sich Unfall ereignet hat
				if (!isInterrupted()) {
					rundeFahren();
				}
				
				++i;
				
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Methode bestimmt die Zeit einer gefahrenen Runde mit der Zufallszahl
	 * zwischen 1 und 100 msec und pausiert den Car-Thread entsprechend lange
	 * und gibt addiert die errechnete Rundenzeit zur Instanzvariable gesamtFahrzeit. 
	 * @throws InterruptedException
	 */
	private void rundeFahren() throws InterruptedException {
		Random random = new Random();
		int rundenZeit = random.nextInt(100 - 1) + 1;
		Thread.sleep(rundenZeit);
		gesamtFahrzeit = gesamtFahrzeit + rundenZeit;
	}

	
	/**
	 * Methode gibt Wert von gesamtFahrzeit zurueck. 
	 * @return int der Instanzvariable gesamtFahrzeit
	 */
	protected int getGesamtFahrzeit() {
		return gesamtFahrzeit;
	}
		
	/**
	 * Methode gibt Wert von startNummer zurueck.
	 * @return int der Instanzvariable startNummer
	 */
	protected int getStartNummer() {
		return startNummer;
	}

	/**
	 * Methode implementiert compareTo fuer die Klasse Car, um nach
	 * der Fahrzeit sortieren zu koennen.
	 * @param auto
	 * @return -1, wenn Fahrtzeit des aktuellen Car-Objekts groesser
	 * ist als des uebergebenen Car-Objekts, ansonsten 1
	 */
	protected int compareTo(Car auto) {
		
		if(this.getGesamtFahrzeit() > auto.getGesamtFahrzeit()) {
			return -1;
		} else {
			return 1;
		}
	}
	
}
