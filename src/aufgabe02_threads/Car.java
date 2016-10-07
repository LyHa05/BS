package aufgabe02_threads;

public class Car extends Thread {
	
	private int startNummer;
	private int gesamtFahrzeit;
	
	public Car(int nummer) {
		this.startNummer = nummer;
	}

	public void run() {
		
		
		
	}
	
	public int getGesamtFahrzeit() {
		return gesamtFahrzeit;
	}
	
}
