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

	public void setKleur(Kleur kleur) {
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

	}

	public void heeftGewonnen() {

	}
	
	public void berekenScore() {
		
	}
	
	public void plaatsKasteel(int x, int y) {
		koninkrijk.setKasteel(x, y, kasteel);
	}

}
