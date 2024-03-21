package domein;

public class DominoTegel {
	private int volgnummer;
	private boolean horizontaal;
	private boolean spiegeld;

	public DominoTegel(int volgnummer) {
		this.volgnummer = volgnummer;
	}

	//Getters
	public int getVolgnummer() {
		return volgnummer;
	}

	public boolean isHorizontaal() {
		return horizontaal;
	}

	public boolean isSpiegeld() {
		return spiegeld;
	}
	
	//Draai de domino
	//Horizontaal of verticaal
	//De domino draait met de klok mee 
	//En tegenkloks terug
	public void draai() {
		
	}
	
	//Spiegel de domino
	//Draait de domino 180°
	public void spiegel() {

	}
}
