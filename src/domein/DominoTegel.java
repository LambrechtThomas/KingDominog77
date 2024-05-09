package domein;

public class DominoTegel {
	private int volgnummer;
	private Tegel[] tegels;
	private boolean horizontaal;
	private boolean spiegeld;

	/**
	 * Dominotegel bestaat uit:
	 * 
	 * @param volgnummer
	 * @param tegels     (de 2 landschappen van de onderlinge tegels)
	 */
	public DominoTegel(int volgnummer, Tegel[] tegels) {
		this.volgnummer = volgnummer;
		this.tegels = tegels;
	}

	// Getters
	/**
	 * Get het volgnummer
	 * 
	 * @return volgnummer
	 */
	public int getVolgnummer() {
		return volgnummer;
	}

	/**
	 * Get de tegels
	 * 
	 * @return tegels
	 */
	public Tegel[] getTegels() {
		return tegels;
	}

	/**
	 * Checkt of het horizontaal is
	 * 
	 * @return true/false
	 */
	public boolean isHorizontaal() {
		return horizontaal;
	}

	/**
	 * Checkt of het gespiegeld is
	 * 
	 * @return true/false
	 */
	public boolean isSpiegeld() {
		return spiegeld;
	}

	/**
	 * Draait de domino horizontaal of verticaal, met de klok mee of tegenkloks terug
	 */
	public void draai() {
		if (horizontaal)
			horizontaal = false;
		else
			horizontaal = true;
	}

	
	/**
	 * Spiegel de domino door deze 180° te draaien
	 */
	public void spiegel() {
		if (spiegeld)
			spiegeld = false;
		else
			spiegeld = true;
	}

}
