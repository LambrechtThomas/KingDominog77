package domein;

public class Tegel {

	private String landschap;
	private int kroontjes;
	public Tegel(String landschap, int kroontjes) {
	
		this.landschap = landschap;
		this.kroontjes = kroontjes;
	}
	public String tegel(String landschap) {
		return landschap;
	}
}
