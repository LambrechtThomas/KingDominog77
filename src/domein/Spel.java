package domein;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

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
			alleDominos.remove(domino);
		}
	}

	// Setters
	public void setStartKolom(ArrayList<DominoTegel> startKolom) {
		this.startKolom = startKolom;
	}

	public void setGebruikteDominos(ArrayList<DominoTegel> gebruikteDominos) {
		this.gebruikteDominos = gebruikteDominos;
	}

	// Getters
	public ArrayList<DominoTegel> getStartKolom() {
		return startKolom;
	}

	public ArrayList<DominoTegel> getEindKolom() {
		return gebruikteDominos;
	}

	public Speler getKoning() {
		return koning;
	}

	public int getRonde() {
		return ronde;
	}

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
	public void plaatsDominoTegel(DominoTegel domino, int rij, int kolom) {
		koning.plaatsDomino(domino, rij, kolom);
	}

	// Schud het deck
	public void schud() {
		Collections.shuffle(alleDominos);
	}

	// De tegels die op tafel liggen (start/eindkolom)
	public void wisselKolomTegel() {
		gebruikteDominos.addAll(startKolom);

		startKolom.clear();

		for (int i = 0; i < huidigeSpelers.size(); i++) {
			startKolom.add(alleDominos.get(0));
			alleDominos.remove(0);
		}

		schud();
	}

	// Kiest een willekeurige koning uit het spel
	public void kiesKoning() {
		Speler gekozen;

		if (spelersAanbeurtTeKomen.isEmpty()) {
			spelersAanbeurtTeKomen.addAll(aandeBeurtGeweest);
			aandeBeurtGeweest.clear();
			ronde++;

			wisselKolomTegel();
		}

		gekozen = spelersAanbeurtTeKomen.get(sr.nextInt(spelersAanbeurtTeKomen.size()));
		spelersAanbeurtTeKomen.remove(gekozen);
		aandeBeurtGeweest.add(gekozen);
		koning = gekozen;
	}

	public Speler geefGewonnenSpeler() {
		Speler gewonenenSpeler = huidigeSpelers.get(0);
		berekenScores();

		for (Speler speler : huidigeSpelers) {
			if (speler.getTotaleScore() > gewonenenSpeler.getTotaleScore())
				gewonenenSpeler = speler;
		}

		return gewonenenSpeler;
	}

	/**
	 * Berekenen van de scores van aparte landschappen om deze daarna via
	 * "berekenScore" op te tellen
	 */
	public void berekenScores() {
		for (Speler speler : huidigeSpelers) {
			speler.setMoerasTegelScores(speler.getKoninkrijk().berekenOppvervlakte("moeras"));
			speler.setBosTegelScores(speler.getKoninkrijk().berekenOppvervlakte("bos"));
			speler.setGrasTegelScores(speler.getKoninkrijk().berekenOppvervlakte("gras"));
			speler.setMijnTegelScores(speler.getKoninkrijk().berekenOppvervlakte("mijn"));
			speler.setWaterTegelScores(speler.getKoninkrijk().berekenOppvervlakte("water"));
			speler.setGraanTegelScores(speler.getKoninkrijk().berekenOppvervlakte("graan"));

			speler.berekenScore();
		}
	}
}
