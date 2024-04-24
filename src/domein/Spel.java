package domein;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;

public class Spel {
	private DominoTegelRepository DominoRepo;
	private ArrayList<DominoTegel> startKolom;
	private ArrayList<DominoTegel> eindKolom;
	private ArrayList<DominoTegel> alleDominos;

	private ArrayList<Speler> huidigeSpelers;
	private Speler koning;
	private ArrayList<Speler> spelersAanbeurtTeKomen;
	private ArrayList<Speler> aandeBeurtGeweest;
	private int ronde;

	private SecureRandom sr;

	public Spel(ArrayList<Speler> huidigeSpelers) {
		DominoRepo = new DominoTegelRepository();
		startKolom = new ArrayList<>();
		eindKolom = new ArrayList<>();
		sr = new SecureRandom();
		huidigeSpelers = new ArrayList<>();
		spelersAanbeurtTeKomen = new ArrayList<>();
		aandeBeurtGeweest = new ArrayList<>();
		// Moet nog gechecked worden als er 3 tot 4 mensen spelen / en als kleueren niet
		// overlappen

		kiesKoning();
	}

	// Setters
	public void setStartKolom(ArrayList<DominoTegel> startKolom) {
		this.startKolom = startKolom;
	}

	public void setEindKolom(ArrayList<DominoTegel> eindKolom) {
		this.eindKolom = eindKolom;
	}

	// Getters
	public ArrayList<DominoTegel> getStartKolom() {
		return startKolom;
	}

	public ArrayList<DominoTegel> getEindKolom() {
		return eindKolom;
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

	// Plaats DominoTegel bij speler
	public void plaatsDominoTegel(Speler speler, DominoTegel domino, int rij, int kolom) {
		speler.plaatsDomino(domino, rij, kolom);
	}

	// Schud het deck
	public void schud() {
		Collections.shuffle(alleDominos);
	}

	// De tegels die op tafel liggen
	public void wisselKolomTegel() {
		eindKolom = startKolom;

		for (int i = 0; i < huidigeSpelers.size(); i++) {
			startKolom.add(alleDominos.get(0));
			alleDominos.remove(0);
		}

		schud();
	}

	// Kiest een willekeurige koning
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

	// bereken van scores van spelers
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
