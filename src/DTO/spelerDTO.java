package DTO;

public record spelerDTO(String gebruikersnaam, int geboortejaar, int score, int aantalGewonnen, int aantalGespeeld) {
	
	public spelerDTO(String gebruikersnaam, int geboortejaar, int score ,int aantalGewonnen, int aantalGespeeld) {
		this.gebruikersnaam = gebruikersnaam;
		this.geboortejaar = geboortejaar;
		this.score = score;
		this.aantalGewonnen = aantalGewonnen;
		this.aantalGespeeld = aantalGespeeld;
	}
	
	@Override
	public String toString() {
		return gebruikersnaam;
	}
}
