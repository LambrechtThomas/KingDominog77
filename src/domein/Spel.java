package domein;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;

public class Spel {
	private DominoTegelRepository DominoRepo;
	private ArrayList<DominoTegel> startKolom;
	private ArrayList<DominoTegel> eindKolom;
	private ArrayList<DominoTegel> alleDominos;

	private ArrayList<Speler> beschikbareSpelers;
	private ArrayList<Speler> huidigeSpelers;
	private Speler koning;
	private Speler spelerAanbeurt;

	private SecureRandom sr;

	public Spel() {
		DominoRepo = new DominoTegelRepository();
		startKolom = new ArrayList<>();
		eindKolom = new ArrayList<>();
		sr = new SecureRandom();
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

	public Speler getSpelerAanbeurt() {
		return spelerAanbeurt;
	}

	// Spel klaar zetten
	public void startSpel() {
		kiesKoning();
	}

	// Plaats DominoTegel bij speler
	public void plaatsDominoTegel(Speler speler, DominoTegel domino, int rij, int kolom) {
		speler.plaatsDomino(domino, rij, kolom);
	}

	// Duidt de volgende speler aan
	public void duidVolgendeSplerAan(DominoTegel domino, int rij, int kolom) {
		int indexOfSpelerAanBeurt = huidigeSpelers.indexOf(spelerAanbeurt);

		if (indexOfSpelerAanBeurt == huidigeSpelers.size() - 1)
			spelerAanbeurt = huidigeSpelers.get(0);
		else
			spelerAanbeurt = huidigeSpelers.get(++indexOfSpelerAanBeurt);
	}

	// Voegt een speler toe aan het spel
	public void voegSpelersToe(ArrayList<Speler> spelers) {
		if (spelers.size() < 3 || spelers.size() > 4)
			throw new IllegalArgumentException();
	}

	// Schud het deck
	public void schud() {
		Collections.shuffle(alleDominos);
	}

	public ArrayList<Kleur> geefBeschikbareKleuren() {
		ArrayList<Kleur> gebruikteKleuren = new ArrayList<>();
		ArrayList<Kleur> nietGebruikteKleuren = new ArrayList<>();

		for (Speler speler : huidigeSpelers) {
			gebruikteKleuren.add(speler.getKleur());
		}

		for (Kleur kleur : Kleur.values()) {
			if (!gebruikteKleuren.contains(kleur))
				nietGebruikteKleuren.add(kleur);
		}

		return nietGebruikteKleuren;
	}

	public void kiesKoning() {
		koning = huidigeSpelers.get(sr.nextInt(huidigeSpelers.size()));
	}

}
