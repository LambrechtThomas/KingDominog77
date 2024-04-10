package DTO;

public record spelerDTO(String gebruikersnaam, int geboortejaar, int aantalGewonnen, int aantalGespeeld) {
	
	public spelerDTO(String gebruikersnaam, int geboortejaar, int aantalGewonnen, int aantalGespeeld) {
		this.gebruikersnaam = gebruikersnaam;
		this.geboortejaar = geboortejaar;
		this.aantalGewonnen = aantalGewonnen;
		this.aantalGespeeld = aantalGespeeld;
	}
}
