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
	
	private int anzahlRunden;
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
		Accident unfall = new Accident();
		sr.rennenVorbereiten();
		sr.rennenBeginnen();

		unfall.start();
		
		// warten bis Unfall passiert oder Rennen beendet wird
		while (!sr.rennenBeendet() && !unfall.unfallVorhanden()) {

		}
		
		if (unfall.unfallVorhanden()) {
			for (Car auto : sr.autoTeilnehmer) {
				auto.interrupt();
			}
			System.err.println("Das Rennen wurde wegen eines Unfalls abgebrochen.");
		} else {
			unfall.interrupt();
			sr.ergebnisAusgeben();
		}
		
	}
	
	/**
	 * Methode prueft, ob Rennen beendet worden ist.
	 * 
	 * @return true, wenn das Rennen beendet worden ist
	 * und alle Autos Ihre Runden beendet haben.
	 * @throws InterruptedException
	 */
	private boolean rennenBeendet() throws InterruptedException {
		for (int i = 0; i < autoTeilnehmer.size(); i++) {
			autoTeilnehmer.get(i).join();
		}
		return true;
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
			Car auto = new Car(i,this.anzahlRunden);
			autoTeilnehmer.add(auto);
			auto.start();
			++i;
		}
		
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
