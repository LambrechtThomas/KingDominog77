package domein;

public class Speler {
	private String gebruikersnaam;
	private int geboortejaar;
	private int aantalGewonnen, aantalGespeeld;
	private String kleur;

	public Speler(String gebruikersnaam, int geboortejaar, int aantalGewonnen, int aantalGespeeld) {
		setGebruikersnaam(gebruikersnaam);
		setGeboortejaar(geboortejaar);
		setAantalGewonnen(aantalGewonnen);
		setAantalGespeeld(aantalGespeeld);
	}

	public Speler(String gebruikersnaam, int geboortejaar) {
		this(gebruikersnaam, geboortejaar, 0, 0);
	}

	// Setters
	private void setGebruikersnaam(String gebruikersnaam) {
		this.gebruikersnaam = gebruikersnaam;
	}

	private void setGeboortejaar(int geboortejaar) {
		this.geboortejaar = geboortejaar;
	}

	private void setAantalGewonnen(int aantalGewonnen) {
		this.aantalGewonnen = aantalGewonnen;
	}

	private void setAantalGespeeld(int aantalGespeeld) {
		this.aantalGespeeld = aantalGespeeld;
	}

	private void setKleur(String kleur) {
		this.kleur = kleur;
	}

	// Getters
	public String getGebruikersnaam() {
		return gebruikersnaam;
	}

	public int getGeboortejaar() {
		return geboortejaar;
	}

	public int getAantalGewonnen() {
		return aantalGewonnen;
	}

	public int getAantalGespeeld() {
		return aantalGespeeld;
	}

	public String getKleur() {
		return kleur;
	}

	public void heeftGespeelt() {

	}

	public void heeftGewonnen() {

	}

}
