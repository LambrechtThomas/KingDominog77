package DTO;

import domein.Kleur;

public record spelerDTO(String gebruikersnaam, int geboortejaar, int score, int aantalGewonnen, int aantalGespeeld, Kleur kleur) {
	
	public spelerDTO(String gebruikersnaam, int geboortejaar, int score ,int aantalGewonnen, int aantalGespeeld, Kleur kleur) {
		this.gebruikersnaam = gebruikersnaam;
		this.geboortejaar = geboortejaar;
		this.score = score;
		this.aantalGewonnen = aantalGewonnen;
		this.aantalGespeeld = aantalGespeeld;
		this.kleur = kleur;
	}
	
	@Override
	public String toString() {
		return gebruikersnaam;
	}
}
