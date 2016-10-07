package aufgabe02_threads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SimRace {
	
	private int autoNummer = 0;
	
	public static void main(String[] args) throws IOException {
		SimRace rennen = new SimRace();
		rennen.beginnRennen();
	}
	
	private boolean eingabePruefen(String eingabe) {
		return eingabe.matches("^[0-9]");
	}
	
	private void beginnRennen() throws IOException {
		
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

	}
	
	public int startNummerVergeben() {
		return ++autoNummer;
	}
}
