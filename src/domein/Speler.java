package domein;

public class Speler {
	private String gebruikersnaam;
	private int geboortejaar;
	private int aantalGewonnen, aantalGespeeld;
	private Kleur kleur;

	private Koninkrijk koninkrijk;
	private KasteelTegel kasteel;

	private int score;

	public Speler(String gebruikersnaam, int geboortejaar, int aantalGewonnen, int aantalGespeeld) {
		setGebruikersnaam(gebruikersnaam);
		setGeboortejaar(geboortejaar);
		setAantalGewonnen(aantalGewonnen);
		setAantalGespeeld(aantalGespeeld);

		this.koninkrijk = new Koninkrijk();
		this.kasteel = new KasteelTegel();
	}

	public Speler(String gebruikersnaam, int geboortejaar) {
		this(gebruikersnaam, geboortejaar, 0, 0);

		this.koninkrijk = new Koninkrijk();
		this.kasteel = new KasteelTegel();
	}

	// Setters
	private void setGebruikersnaam(String gebruikersnaam) {

		if (gebruikersnaam == null || gebruikersnaam.isBlank())
			throw new IllegalArgumentException("naam kan enkel letters bevatten.");

		this.gebruikersnaam = gebruikersnaam;
	}

	private void setGeboortejaar(int geboortejaar) {

		int MIN_YEAR = 1900;
		int MAX_YEAR = 2024;

		if (geboortejaar < MIN_YEAR || geboortejaar > MAX_YEAR)
			throw new IllegalArgumentException(
					String.format("Geboortejaar moet tussen %d en %d liggen.", MIN_YEAR, MAX_YEAR));

		this.geboortejaar = geboortejaar;
	}

	private void setAantalGewonnen(int aantalGewonnen) {

		if (aantalGewonnen < 0)
			throw new IllegalArgumentException("aantalGewonnen kan niet negatief zijn.");

		this.aantalGewonnen = aantalGewonnen;
	}

	private void setAantalGespeeld(int aantalGespeeld) {

		if (aantalGespeeld < 0)
			throw new IllegalArgumentException("aantalGespeeld kan niet negatief zijn.");

		this.aantalGespeeld = aantalGespeeld;
	}

	public void setKleur(Kleur kleur) {

		if (kleur == null)
			throw new IllegalArgumentException("Je hebt geen kleur geselecteerd.");

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

	public Kleur getKleur() {
		return kleur;
	}

	public Koninkrijk getKoninkrijk() {
		return koninkrijk;
	}

	public KasteelTegel getKasteel() {
		return kasteel;
	}

	public int getScore() {
		return score;
	}

	// Methodes
	public void heeftGespeelt() {
		aantalGespeeld++;
	}

	public void heeftGewonnen() {
		aantalGewonnen++;
	}

	public void berekenScore() {

	}

	public void plaatsDomino(DominoTegel domino, int rij, int kolom) {
		koninkrijk.plaatsDomino(domino, rij, kolom);
	}

	public void plaatsKasteel(int x, int y) {
		koninkrijk.setKasteel(x, y, kasteel);
	}

}
