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
	private int rondeTeller;
	
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
		return huidigeSpelers.get(rondeTeller);
	}
	
	// Spel klaar zetten
	public void startSpel() {
		kiesKoning();
		rondeTeller = huidigeSpelers.indexOf(koning);
	}
	
	// Speelt een volgende ronde
	public void speelRonde() {
		
	}

	// Schud het deck
	public void schud() {
		Collections.shuffle(alleDominos);
	}
	
	//Geeft de beschikbare kleueren terug
	public ArrayList<Kleur> geefBeschikbareKleuren() {
		ArrayList<Kleur> gebruikteKleuren = new ArrayList<>();
		ArrayList<Kleur> nietGebruikteKleuren = new ArrayList<>();
		
		for (Speler speler : huidigeSpelers) {
			gebruikteKleuren.add(speler.getKleur());
		}
		
		for(Kleur kleur : Kleur.values()) {
			if (!gebruikteKleuren.contains(kleur))
				nietGebruikteKleuren.add(kleur);
		}
		
		return nietGebruikteKleuren;
	}
	
	public void kiesKoning() {
		koning = huidigeSpelers.get(sr.nextInt(huidigeSpelers.size()));
	}

}
