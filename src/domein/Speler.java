package domein;

import java.time.Year;
import java.util.ArrayList;

// Speler aanmaken met attributen: 
// gebruikersnaam, geboortejaar, aantalGewonnen, aantalGespeeld, kleur
// Scores van aparte landschappen wordt bijgehouden (default 0, ergens anders berekent)

public class Speler {
	private String gebruikersnaam;
	private int geboortejaar;
	private int aantalGewonnen;
	private int aantalGespeeld;
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
		totaleScore = 0;

		this.koninkrijk = new Koninkrijk();
		this.kasteel = new KasteelTegel();
	}

	public Speler(String gebruikersnaam, int geboortejaar) {
		this(gebruikersnaam, geboortejaar, 0, 0);

		this.koninkrijk = new Koninkrijk();
		this.kasteel = new KasteelTegel();
	}

	// Setters

	/**
	 * Check gebruikersnaam: mag enkel letters bevatten
	 * 
	 * @param gebruikersnaam
	 */
	private void setGebruikersnaam(String gebruikersnaam) {

		if (gebruikersnaam == null || gebruikersnaam.isBlank())
			throw new IllegalArgumentException("naam kan enkel letters bevatten.");

		this.gebruikersnaam = gebruikersnaam;
	}

	/**
	 * Check geboortejaar: moet tussen (Huidig jaar - 121 en Huidig jaar) liggen
	 * 
	 * @param geboortejaar
	 */
	private void setGeboortejaar(int geboortejaar) {

		if (geboortejaar < MIN_YEAR || geboortejaar > MAX_YEAR)
			throw new IllegalArgumentException(
					String.format("Geboortejaar moet tussen %d en %d liggen.", MIN_YEAR, MAX_YEAR));

		this.geboortejaar = geboortejaar;
	}

	/**
	 * Check aantalGewonnen: niet negatief
	 * 
	 * @param aantalGewonnen
	 */
	private void setAantalGewonnen(int aantalGewonnen) {

		if (aantalGewonnen < 0)
			throw new IllegalArgumentException("aantalGewonnen kan niet negatief zijn.");

		this.aantalGewonnen = aantalGewonnen;
	}

	/**
	 * Check aantalGespeeld: niet negatief
	 * 
	 * @param aantalGespeeld
	 */
	private void setAantalGespeeld(int aantalGespeeld) {

		if (aantalGespeeld < 0)
			throw new IllegalArgumentException("aantalGespeeld kan niet negatief zijn.");

		this.aantalGespeeld = aantalGespeeld;
	}

	/**
	 * Check kleur: kleur geselecteerd. Wat als kleur overlapt met kleur van andere speler?
	 * 
	 * @param kleur
	 */
	public void setKleur(Kleur kleur) {

		if (kleur == null)
			throw new IllegalArgumentException("Je hebt geen kleur geselecteerd.");

		this.kleur = kleur;
	}

	// Setters voor de list<list>
	
	/**
	 * Set de moerastegelscore
	 * 
	 * @param moerasTegelScores
	 */
	public void setMoerasTegelScores(ArrayList<ArrayList<Integer>> moerasTegelScores) {
		this.moerasTegelScores = moerasTegelScores;
	}

	/**
	 * Set de bostegelscore
	 * 
	 * @param bosTegelScores
	 */
	public void setBosTegelScores(ArrayList<ArrayList<Integer>> bosTegelScores) {
		this.bosTegelScores = bosTegelScores;
	}

	/**
	 * Set de bostegelscore
	 * 
	 * @param grasTegelScores
	 */
	public void setGrasTegelScores(ArrayList<ArrayList<Integer>> grasTegelScores) {
		this.grasTegelScores = grasTegelScores;
	}

	/**
	 * Set de mijntegelscore
	 * 
	 * @param mijnTegelScores
	 */
	public void setMijnTegelScores(ArrayList<ArrayList<Integer>> mijnTegelScores) {
		this.mijnTegelScores = mijnTegelScores;
	}

	/**
	 * Set de mijntegelscore
	 * 
	 * @param waterTegelScores
	 */
	public void setWaterTegelScores(ArrayList<ArrayList<Integer>> waterTegelScores) {
		this.waterTegelScores = waterTegelScores;
	}

	/**
	 * Set de graantegelscore
	 * 
	 * @param graanTegelScores
	 */
	public void setGraanTegelScores(ArrayList<ArrayList<Integer>> graanTegelScores) {
		this.graanTegelScores = graanTegelScores;
	}

	// Getters
	
	/**
	 * Get de gebruikersnaam
	 * 
	 * @return gebruikersnaam
	 */
	public String getGebruikersnaam() {
		return gebruikersnaam;
	}

	/**
	 * Get het geboortejaar
	 * 
	 * @return geboortejaar
	 */
	public int getGeboortejaar() {
		return geboortejaar;
	}

	/**
	 * Get het aantal keren gewonnen
	 * 
	 * @return aantalGewonnen
	 */
	public int getAantalGewonnen() {
		return aantalGewonnen;
	}

	/**
	 * Get het aantal keren gespeeld
	 * 
	 * @return aantalGespeeld
	 */
	public int getAantalGespeeld() {
		return aantalGespeeld;
	}

	/**
	 * Get het kleur
	 * 
	 * @return kleur
	 */
	public Kleur getKleur() {
		return kleur;
	}

	/**
	 * Get het koninkrijk
	 * 
	 * @return koninkrijk
	 */
	public Koninkrijk getKoninkrijk() {
		return koninkrijk;
	}

	/**
	 * Get het kasteel 
	 * 
	 * @return kasteel
	 */
	public KasteelTegel getKasteel() {
		return kasteel;
	}

	/**
	 * Get de totale score
	 * 
	 * @return	totaleScore
	 */
	public int getTotaleScore() {
		return totaleScore;
	}

	/**
	 * Get de scores van de moerastegel
	 * 
	 * @return moerasTegelScores
	 */
	public ArrayList<ArrayList<Integer>> getMoerasTegelScores() {
		return moerasTegelScores;
	}

	/**
	 * Get de scores van de bostegel
	 * 
	 * @return bosTegelScores
	 */
	public ArrayList<ArrayList<Integer>> getBosTegelScores() {
		return bosTegelScores;
	}

	public ArrayList<ArrayList<Integer>> getGrasTegelScores() {
		return grasTegelScores;
	}

	/**
	 * Get de scores van de mijntegel
	 * 
	 * @return mijnTegelScores
	 */
	public ArrayList<ArrayList<Integer>> getMijnTegelScores() {
		return mijnTegelScores;
	}

	/**
	 * Get de scores van de watertegel
	 * 
	 * @return waterTegelScores
	 */
	public ArrayList<ArrayList<Integer>> getWaterTegelScores() {
		return waterTegelScores;
	}

	/**
	 * Get de scores van de graantegel
	 * 
	 * @return graanTegelScores
	 */
	public ArrayList<ArrayList<Integer>> getGraanTegelScores() {
		return graanTegelScores;
	}

	/**
	 * Plaatst het kasteel op het bord (koninkrijk)
	 * 
	 * @param x coördinaat
	 * @param y coördinaat
	 */
	public void plaatsKasteel(int x, int y) {
		koninkrijk.setKasteel(x, y, kasteel);
	}

	/**
	 * Plaatst een domino op het bord (koninkrijk)
	 * 
	 * @param domino
	 * @param rij    x coördinaat
	 * @param kolom  y coördinaat
	 */
	public void plaatsDomino(DominoTegel domino, int rij, int kolom) {
		koninkrijk.plaatsDomino(domino, rij, kolom);
	}

	/**
	 * Bereken de scores van de aparte landschappen door de grootte van de
	 * oppervlakken x aantal kroontjes te doen en voeg ze samen voor
	 * de eindscore
	 */
	public void berekenScore() {
		moerasScore = berekenTotaleScoreDomein(bosTegelScores);
		bosScore = berekenTotaleScoreDomein(bosTegelScores);
		grasScore = berekenTotaleScoreDomein(bosTegelScores);
		mijnScore = berekenTotaleScoreDomein(mijnTegelScores);
		waterScore = berekenTotaleScoreDomein(waterTegelScores);
		graanScore = berekenTotaleScoreDomein(graanTegelScores);

		totaleScore = moerasScore + bosScore + grasScore + mijnScore + waterScore + graanScore;

	}

	/**
	 * Hulpmethode om landschap makkelijker te kunnen berekenen
	 * 
	 * @param lijst
	 * @return score
	 */
	public int berekenTotaleScoreDomein(ArrayList<ArrayList<Integer>> lijst) {
		int score = 0;

		for (ArrayList<Integer> integers : lijst) {
			score += integers.get(0) * integers.get(1);
		}

		return score;
	}


}
