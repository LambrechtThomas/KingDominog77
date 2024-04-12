package domein;

import java.time.Year;
import java.util.ArrayList;

// Speler aanmaken met attributen: 
// gebruikersnaam, geboortejaar, aantalGewonnen, aantalGespeeld, kleur
// Scores van aparte landschappen wordt bijgehouden

public class Speler {
	private String gebruikersnaam;
	private int geboortejaar;
	private int aantalGewonnen, aantalGespeeld;
	private Kleur kleur;

	private Koninkrijk koninkrijk;
	private KasteelTegel kasteel;

	private int totaleScore;

	private int moerasScore = 0;
	private int bosScore = 0;
	private int grasScore = 0;
	private int mijnScore = 0;
	private int waterScore = 0;
	private int graanScore = 0;

	private ArrayList<ArrayList<Integer>> moerasTegelScores = new ArrayList<>();
	private ArrayList<ArrayList<Integer>> bosTegelScores = new ArrayList<>();
	private ArrayList<ArrayList<Integer>> grasTegelScores = new ArrayList<>();
	private ArrayList<ArrayList<Integer>> mijnTegelScores = new ArrayList<>();
	private ArrayList<ArrayList<Integer>> waterTegelScores = new ArrayList<>();
	private ArrayList<ArrayList<Integer>> graanTegelScores = new ArrayList<>();

	private static final int MIN_YEAR = Year.now().getValue() - 121;
	private static final int MAX_YEAR = Year.now().getValue();

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

	// Check gebruikersnaam: mag enkel letters bevatten
	private void setGebruikersnaam(String gebruikersnaam) {

		if (gebruikersnaam == null || gebruikersnaam.isBlank())
			throw new IllegalArgumentException("naam kan enkel letters bevatten.");

		this.gebruikersnaam = gebruikersnaam;
	}

	// Check geboortejaar: moet tussen (Huidig jaar - 121 en Huidig jaar) liggen
	private void setGeboortejaar(int geboortejaar) {

		if (geboortejaar < MIN_YEAR || geboortejaar > MAX_YEAR)
			throw new IllegalArgumentException(
					String.format("Geboortejaar moet tussen %d en %d liggen.", MIN_YEAR, MAX_YEAR));

		this.geboortejaar = geboortejaar;
	}

	// Check aantalGewonnen: niet negatief
	private void setAantalGewonnen(int aantalGewonnen) {

		if (aantalGewonnen < 0)
			throw new IllegalArgumentException("aantalGewonnen kan niet negatief zijn.");

		this.aantalGewonnen = aantalGewonnen;
	}

	// Check aantalGespeeld: niet negatief
	private void setAantalGespeeld(int aantalGespeeld) {

		if (aantalGespeeld < 0)
			throw new IllegalArgumentException("aantalGespeeld kan niet negatief zijn.");

		this.aantalGespeeld = aantalGespeeld;
	}

	// Check kleur: kleur geselecteerd
	// wat als kleur overlapt met kleur van andere speler?
	public void setKleur(Kleur kleur) {

		if (kleur == null)
			throw new IllegalArgumentException("Je hebt geen kleur geselecteerd.");

		this.kleur = kleur;
	}

	// Setters voor de list<list>
	public void setMoerasTegelScores(ArrayList<ArrayList<Integer>> moerasTegelScores) {
		this.moerasTegelScores = moerasTegelScores;
	}

	public void setBosTegelScores(ArrayList<ArrayList<Integer>> bosTegelScores) {
		this.bosTegelScores = bosTegelScores;
	}

	public void setGrasTegelScores(ArrayList<ArrayList<Integer>> grasTegelScores) {
		this.grasTegelScores = grasTegelScores;
	}

	public void setMijnTegelScores(ArrayList<ArrayList<Integer>> mijnTegelScores) {
		this.mijnTegelScores = mijnTegelScores;
	}

	public void setWaterTegelScores(ArrayList<ArrayList<Integer>> waterTegelScores) {
		this.waterTegelScores = waterTegelScores;
	}

	public void setGraanTegelScores(ArrayList<ArrayList<Integer>> graanTegelScores) {
		this.graanTegelScores = graanTegelScores;
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

	public int getTotaleScore() {
		return totaleScore;
	}

	public ArrayList<ArrayList<Integer>> getMoerasTegelScores() {
		return moerasTegelScores;
	}

	public ArrayList<ArrayList<Integer>> getBosTegelScores() {
		return bosTegelScores;
	}

	public ArrayList<ArrayList<Integer>> getGrasTegelScores() {
		return grasTegelScores;
	}

	public ArrayList<ArrayList<Integer>> getMijnTegelScores() {
		return mijnTegelScores;
	}

	public ArrayList<ArrayList<Integer>> getWaterTegelScores() {
		return waterTegelScores;
	}

	public ArrayList<ArrayList<Integer>> getGraanTegelScores() {
		return graanTegelScores;
	}

	// plaatst het kasteel in het koninkrijk
	public void plaatsKasteel(int x, int y) {
		koninkrijk.setKasteel(x, y, kasteel);
	}

	// plaatst de domino in koninkrijk
	public void plaatsDomino(DominoTegel domino, int rij, int kolom) {
		koninkrijk.plaatsDomino(domino, rij, kolom);
	}

	// Bereken de scores van de aparte landschappen en voeg ze samen voor de
	// eindscore
	public void berekenScore() {
		totaleScore = 0;

		moerasScore = berekenTotaleScoreDomein(bosTegelScores);
		bosScore = berekenTotaleScoreDomein(bosTegelScores);
		grasScore = berekenTotaleScoreDomein(bosTegelScores);
		mijnScore = berekenTotaleScoreDomein(mijnTegelScores);
		waterScore = berekenTotaleScoreDomein(waterTegelScores);
		graanScore = berekenTotaleScoreDomein(graanTegelScores);

		totaleScore = moerasScore + bosScore + grasScore + mijnScore + waterScore + graanScore;

	}

	// hulp methode om landschap makkelijker te kunnen berekenen
	public int berekenTotaleScoreDomein(ArrayList<ArrayList<Integer>> lijst) {
		int score = 0;

		for (ArrayList<Integer> integers : lijst) {
			score += integers.get(0) * integers.get(1);
		}

		return score;
	}

}
