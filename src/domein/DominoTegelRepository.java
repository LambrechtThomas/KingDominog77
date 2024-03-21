package domein;

import java.util.ArrayList;
import java.util.Collections;

public class DominoTegelRepository {
	private ArrayList<DominoTegel> alleDominos;

	public DominoTegelRepository() {
	}
	
	// Schud het deck
	public void schud() {
		Collections.shuffle(alleDominos);
	}
	
	// Returnt de eerste domino op het deck
	public DominoTegel geefDomino() {
		DominoTegel eersteTegel = alleDominos.get(0);
		alleDominos.remove(0);
		
		return eersteTegel; 
	}
	
	// Returnt volledige lijst van dominos
	public ArrayList<DominoTegel> geefLijstDominos() {
		return alleDominos;
	}

	// Geeft een lijst van domino's terug (aan de hand van de aantal spelers)
	public ArrayList<DominoTegel> geefKolom(int aantalSpelers) {
		ArrayList<DominoTegel> dominos = new ArrayList<DominoTegel>();
		for(int i = 0; i < aantalSpelers; i++) {
			dominos.add(geefDomino());
		}
		return dominos;
	}
	
	
}
