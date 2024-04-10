package domein;

import java.util.ArrayList;

public class DomeinController {

	private final SpelerRepository spelerRepository;
	private Spel huidigSpel;
	
	private ArrayList<Speler> deelnemendeSpelers;
	private ArrayList<Speler> beschikbareSpelers;

	public DomeinController() {
		spelerRepository = new SpelerRepository();
	}

	public void registreerSpeler(String gebruikersnaam, int geboortejaar) {
		Speler nieuweSpeler = new Speler(gebruikersnaam, geboortejaar);
		spelerRepository.voegToe(nieuweSpeler);
	}
	
	public void spelerDoetMee (String gebruikersnaam) {
		// check of gebruikers naam in data bank zit
		
		deelnemendeSpelers.add(null);
	}

	public void startSpel() {
		//Nakijken of deelnemendeSpelers 3 of 4 zijn en of ze kleuren hebben
		
		huidigSpel = new Spel(deelnemendeSpelers);
	}
	
	public void volgendeRonde() {
		huidigSpel.duidVolgendeSpelerAan();
	}

//	public ArrayList<SpelerDTO> geefBeschikbareSpelers() {
//
//	}

	public ArrayList<Kleur> geefBeschikbareKleuren() {
		return huidigSpel.geefBeschikbareKleuren();
	}

	// public SpelerDTO geefSpelerAanbeurt() {}

}
