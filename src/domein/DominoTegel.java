package domein;

public class DominoTegel {
	private int volgnummer;
	private boolean horizontaal;
	private boolean spiegeld;
	private Tegel[] tegels;

	public DominoTegel(int volgnummer, Tegel[] tegels) {
		this.volgnummer = volgnummer;
		this.tegels = tegels;
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
		if (horizontaal)
			horizontaal = false;
		else
			horizontaal = true;
	}
	
	//Spiegel de domino
	//Draait de domino 180°
	public void spiegel() {
		if (spiegeld)
			spiegeld = false;
		else
			spiegeld = true;
	}
}
