package DTO;

public record tegelDTO(String landschap, int kroontjes) {

	public tegelDTO(String landschap, int kroontjes) {
		this.landschap = landschap;
		this.kroontjes = kroontjes;
	}
}
