package aufgabe02_threads;

import java.util.Random;

public class Car extends Thread {
	
	private int startNummer;
	private int gesamtFahrzeit;
	private int anzahlZuFahrendeRunden;
	private boolean rennenBeendet = false;
	
	public Car(int nummer, int runden) {
		this.startNummer = nummer;
		this.anzahlZuFahrendeRunden = runden;
	}

	public void run() {
		try {
			
			int i = 1;
			while (i <= anzahlZuFahrendeRunden) {
				rundeFahren();
				++i;
			}
			
			rennenBeendet = true;
			boolean inter = Thread.currentThread().interrupted();
			
			System.out.println("interrupt: " + inter);
			
		} catch (InterruptedException e) {
			System.err.println("Eine InterruptedException!");
			e.printStackTrace();
		}
	}
	
	private void rundeFahren() throws InterruptedException {
		Random random = new Random();
		int rundenZeit = random.nextInt(100 - 1) + 1;
		// TODO Car oder Thread fuer Aufruf verwenden?
		Thread.sleep(rundenZeit);
		gesamtFahrzeit = gesamtFahrzeit + rundenZeit;
	}

	protected int getGesamtFahrzeit() {
		return gesamtFahrzeit;
	}
	
	private boolean getRennenBeendet() {
		return rennenBeendet;
	}
	
	protected int getStartNummer() {
		return startNummer;
	}

	protected int compareTo(Car auto) {
		
		if(this.getGesamtFahrzeit() > auto.getGesamtFahrzeit()) {
			return -1;
		} else {
			return 1;
		}
	}
	
}
