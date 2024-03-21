package domein;

public class Tegel {

	private final String landschap;
	private final int kroontjes;
	
	public Tegel(String landschap, int kroontjes) {
		this.landschap = landschap;
		this.kroontjes = kroontjes;
	}
	
	public Tegel() {
		//Deze constructor wordt gebruikt zodat "KasteelTegel" kan overerven
		//Vul attributen aan zodat we weten dat het de kasteel is
		this.landschap = "Kasteel";
		this.kroontjes = 0;
	}
	
	//Getters
	public String getLandschap() {
		return landschap;
	}

	public int getKroontjes() {
		return kroontjes;
	}
}
