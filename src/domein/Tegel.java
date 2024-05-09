package domein;

public class Tegel {

	private static final int MIN_AANTAL_KROONTJES = 0;
	private static final int MAX_AANTAL_KROONTJES = 3;

	private final String landschap;
	private final int kroontjes;

	/**
	 * Maak een tegel aan met daarbij het landschap en het aantal kroontjes
	 * 
	 * @param landschap
	 * @param kroontjes
	 */
	public Tegel(String landschap, int kroontjes) {

		// Check Tegel: kroontjes tussen 0 en 3
		if (kroontjes < MIN_AANTAL_KROONTJES || kroontjes > MAX_AANTAL_KROONTJES)
			throw new IllegalArgumentException(String.format("Een tegel moet tussen %d en %d kroontjes hebben",
					MIN_AANTAL_KROONTJES, MAX_AANTAL_KROONTJES));

		this.landschap = landschap;
		this.kroontjes = kroontjes;
	}

	public Tegel() {
		// Deze constructor wordt gebruikt zodat "KasteelTegel" kan overerven
		// Vul attributen aan zodat we weten dat het de kasteel is
		this.landschap = "Kasteel";
		this.kroontjes = 0;
	}

	// Getters
	
	/**
	 * Get het landschap
	 * 
	 * @return landschap
	 */
	public String getLandschap() {
		return landschap;
	}

	/**
	 * Get kroontjes
	 * 
	 * @return kroontjes
	 */
	public int getKroontjes() {
		return kroontjes;
	}
}
