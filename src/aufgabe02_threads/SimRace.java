package aufgabe02_threads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class SimRace {
	
	private static int anzahlRunden;
	private int anzahlAutos;
	private ArrayList<Car> autoTeilnehmer;
	
	public static void main(String[] args) throws IOException, InterruptedException {
		SimRace sr = new SimRace();
		sr.rennenVorbereiten();
		sr.rennenBeginnen();
		
//		while (!sr.rennenBeendet()) {
//			// warten bis beendet
//		}
		
		Thread.sleep(100 * anzahlRunden);
		
		sr.ergebnisAusgeben();
	}
	
	private void rennenVorbereiten() throws IOException {
		
	    InputStreamReader isr = new InputStreamReader(System.in);
	    BufferedReader br = new BufferedReader(isr);
	    String anzahlAutos = "";
		String anzahlRunden = "";
	    
		do {
			System.out.println("Bitte geben Sie die Anzahl der Autos ein, "
					+ "die im Rennen starten sollen.");
			anzahlAutos = br.readLine();
		} while (!eingabePruefen(anzahlAutos));
		
		do {
			System.out.println("Bitte geben Sie die Anzahl der Runden ein, "
					+ "die im Rennen gefahren werden sollen.");
			anzahlRunden = br.readLine();
		} while (!eingabePruefen(anzahlRunden));
	
		this.anzahlAutos = Integer.parseInt(anzahlAutos);
		this.anzahlRunden = Integer.parseInt(anzahlRunden);
		
	}

	private void rennenBeginnen() {
		
		int i = 1;
		autoTeilnehmer = new ArrayList<>();
		
		while (i <= anzahlAutos) {
			Car auto = new Car(i,anzahlRunden);
			autoTeilnehmer.add(auto);
			auto.start();
			++i;
		}
		
	}

	private boolean rennenBeendet() {
		
		boolean beendet = true;
		
		for(Car auto : autoTeilnehmer) {
			if (!auto.isInterrupted()) {
				beendet = false;
			}
		}
		
		return beendet;
		
	}

	private void ergebnisAusgeben() {
				
		Collections.sort(autoTeilnehmer, (auto1,auto2) -> auto2.compareTo(auto1));
		
		System.out.println("\t **** Endstand ****");
		
		for (int j = 0; j < autoTeilnehmer.size(); j++) {

			System.out.println((j+1) + ". Platz: \t Auto " + autoTeilnehmer.get(j).getStartNummer() + " \t Zeit: " + autoTeilnehmer.get(j).getGesamtFahrzeit());
			
		}
		
	}

	private boolean eingabePruefen(String eingabe) {
		return eingabe.matches("^[0-9]+");
	}
	
}
