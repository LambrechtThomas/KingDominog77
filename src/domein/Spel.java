package domein;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.stream.Collectors;

public class Spel {
	private ArrayList<DominoTegel> startKolom;
	private ArrayList<DominoTegel> gebruikteDominos;
	private ArrayList<DominoTegel> alleDominos;

	private ArrayList<Speler> huidigeSpelers;
	private Speler koning;
	private ArrayList<Speler> spelersAanbeurtTeKomen;
	private ArrayList<Speler> aandeBeurtGeweest;
	private int ronde;

	private SecureRandom sr;

	/**
	 * Nieuw spel starten => variabelen instellen die later nodig zijn in het spel
	 * 
	 * @param huidigeSpelers is een array van de spelers die mee doen met het huidig
	 *                       spel
	 * @param alleDominos    eerder gegenereerde lijst van alle domino's
	 */
	public Spel(ArrayList<Speler> huidigeSpelers, ArrayList<DominoTegel> alleDominos) {
		this.alleDominos = alleDominos;
		startKolom = new ArrayList<>();
		gebruikteDominos = new ArrayList<>();

		sr = new SecureRandom();

		this.huidigeSpelers = huidigeSpelers;
		spelersAanbeurtTeKomen = new ArrayList<>();
		aandeBeurtGeweest = new ArrayList<>();
		spelersAanbeurtTeKomen.addAll(this.huidigeSpelers);
		ronde = 1;

		kiesKoning();
		schud();

		for (int i = 0; i < huidigeSpelers.size(); i++) {
			int randomGetal = sr.nextInt(alleDominos.size());
			DominoTegel domino = alleDominos.get(randomGetal);
			startKolom.add(domino);
		}
		gebruikteDominos.addAll(startKolom);
	}

	// Setters
	/**
	 * Set de startkolom
	 * 
	 * @param startKolom
	 */
	public void setStartKolom(ArrayList<DominoTegel> startKolom) {
		this.startKolom = startKolom;
	}

	// Getters

	/**
	 * Get de startkolom
	 * 
	 * @return startKolom
	 */
	public ArrayList<DominoTegel> getStartKolom() {
		return startKolom;
	}

	/**
	 * Get de eindkolom
	 * 
	 * @return gebruikteDominos
	 */
	public ArrayList<DominoTegel> getEindKolom() {
		return gebruikteDominos;
	}

	/**
	 * Get de koning
	 * 
	 * @return koning
	 */
	public Speler getKoning() {
		return koning;
	}

	/**
	 * Get de ronde
	 * 
	 * @return ronde
	 */
	public int getRonde() {
		return ronde;
	}

	/**
	 * Get de huidige spelers
	 * 
	 * @return huidigeSpelers
	 */
	public ArrayList<Speler> getHuidigeSpelers() {
		return huidigeSpelers;
	}

	/**
	 * Plaats een bepaalde DominoTegel in de grid(rij,kolom) van koning
	 * 
	 * @param domino
	 * @param rij    het x coördinaat van het bord
	 * @param kolom  het y coördinaat van het bord
	 */
	public void plaatsDominoTegel(int dominoNummer, int rij, int kolom) throws Exception {
		koning.plaatsDomino(alleDominos.stream().filter(v -> v.getVolgnummer() == dominoNummer).findFirst().get(), rij,
				kolom);
	}

	/**
	 * Schud het deck
	 */
	public void schud() {
		Collections.shuffle(alleDominos);
	}

	/**
	 * De tegels die op tafel liggen (start/eindkolom)
	 */
	public void wisselKolomTegel() {
		startKolom.clear();

		for (int i = 0; i < huidigeSpelers.size(); i++) {
			startKolom.add(alleDominos.stream().filter(v -> !gebruikteDominos.contains(v)).collect(Collectors.toList()).get(i));
		}
		
		gebruikteDominos.addAll(startKolom);

		schud();
	}

	/**
	 * Kiest een willekeurige koning uit het spel
	 */
	public void kiesKoning() {
		Speler gekozen;

		if (spelersAanbeurtTeKomen.isEmpty()) {
			spelersAanbeurtTeKomen.addAll(aandeBeurtGeweest);
			aandeBeurtGeweest.clear();
			ronde++;
		}

		gekozen = spelersAanbeurtTeKomen.get(sr.nextInt(spelersAanbeurtTeKomen.size()));
		spelersAanbeurtTeKomen.remove(gekozen);
		aandeBeurtGeweest.add(gekozen);
		koning = gekozen;
	}

	/**
	 * Berekent de scores van aparte landschappen om deze daarna via "berekenScore"
	 * op te tellen
	 */
	public void berekenScores() {
		for (Speler speler : huidigeSpelers) {
			speler.setMoerasTegelScores(speler.getKoninkrijk().berekenOppervlakte("moeras"));
			speler.setBosTegelScores(speler.getKoninkrijk().berekenOppervlakte("bos"));
			speler.setGrasTegelScores(speler.getKoninkrijk().berekenOppervlakte("gras"));
			speler.setMijnTegelScores(speler.getKoninkrijk().berekenOppervlakte("mijn"));
			speler.setWaterTegelScores(speler.getKoninkrijk().berekenOppervlakte("water"));
			speler.setGraanTegelScores(speler.getKoninkrijk().berekenOppervlakte("graan"));

			speler.berekenScore();
		}
	}

	public void draaiDomino(int volgnummer) {
		alleDominos.stream().filter(v -> v.getVolgnummer() == volgnummer).findFirst().get().draai();
	}

	public void spiegelDomino(int volgnummer) {
		alleDominos.stream().filter(v -> v.getVolgnummer() == volgnummer).findFirst().get().spiegel();
		;
	}

	/**
	 * Geeft de Winnaar speler
	 * 
	 * @return Winnaar
	 */
	public Speler geefWinnaar() {
		berekenScores();

		return huidigeSpelers.stream()
				.reduce((prev, curr) -> prev.getTotaleScore() > curr.getTotaleScore() ? prev : curr).get();
	}
}
