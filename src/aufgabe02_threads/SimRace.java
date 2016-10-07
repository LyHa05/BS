package aufgabe02_threads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Lucas Anders, Lydia Pflug
 * @date 07.10.2016
 * Die Klasse simuliert ein Autorennen.
 */

public class SimRace {
	
	private static int anzahlRunden;
	private int anzahlAutos;
	private ArrayList<Car> autoTeilnehmer;
	
	
	/**
	 * main() Methode startet das Rennen und ruft die hierfuer
	 * notwendigen Methoden auf.
	 * 
	 * @param args
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws IOException, InterruptedException {
		SimRace sr = new SimRace();
		sr.rennenVorbereiten();
		sr.rennenBeginnen();
		
		Accident.unfallGenerieren();
		
//		while (!sr.rennenBeendet() && !Accident.unfallVorhanden()) {
//			// warten bis beendet
//		}
		
		Thread.sleep(100 * anzahlRunden);
		
		if (Accident.unfallVorhanden()) {
			System.out.println("Das Rennen wurde wegen eines Unfalls abgebrochen.");
		} else {
			sr.ergebnisAusgeben();
		}
		
	}
	
	/**
	 * Die Methode fragt fuer das Rennen die teilnehmenden Autos ab
	 * und die Anzahl der zu fahrenden Runden. 
	 * 
	 * @throws IOException
	 */
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
	
	/**
	 * Die Methode startet das Rennen und erzeugt entsprechend
	 * viele Autos bzw. Threads und startet diese.
	 */
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

	/**
	 * Methode prueft, ob Rennen beendet worden ist.
	 * 
	 * @return true, wenn das Rennen beendet worden ist
	 * und alle Autos Ihre Runden beendet haben.
	 */
	private boolean rennenBeendet() {
		
		boolean beendet = true;
		
		for(Car auto : autoTeilnehmer) {
			if (!auto.isInterrupted()) {
				beendet = false;
			}
		}
		
		return beendet;
		
	}

	/**
	 * Methode sortiert die teilnehmenden Autos nach ihrer gefahrenen Zeit 
	 * und gibt die Rangfolge auf der Konsole aus.
	 */
	private void ergebnisAusgeben() {
				
		Collections.sort(autoTeilnehmer, (auto1,auto2) -> auto2.compareTo(auto1));
		
		System.out.println("\t **** Endstand ****");
		
		for (int j = 0; j < autoTeilnehmer.size(); j++) {

			System.out.println((j+1) + ". Platz: \t Auto " + autoTeilnehmer.get(j).getStartNummer() + " \t Zeit: " + autoTeilnehmer.get(j).getGesamtFahrzeit());
			
		}
	}
	
	/**
	 * Methode prueft Eingabe, ob nur Zahlen eingegeben worden sind. 
	 * 
	 * @param eingabe
	 * @return true, wenn die Eingabe korrekt ist.
	 */
	private boolean eingabePruefen(String eingabe) {
		return eingabe.matches("^[0-9]+");
	}
	
}
