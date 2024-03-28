package domein;

import java.util.ArrayList;

public class DomeinController {

	private final SpelerRepository spelerRepository;
	private Spel huidigSpel;

	public DomeinController() {
		spelerRepository = new SpelerRepository();
	}

	public void registreerSpeler(String gebruikersnaam, int geboortejaar) {
		Speler nieuweSpeler = new Speler(gebruikersnaam, geboortejaar);
		spelerRepository.voegToe(nieuweSpeler);
	}

	public void startSpel() {

	}

//	public ArrayList<SpelerDTO> geefBeschikbareSpelers() {
//
//	}

	public ArrayList<Kleur> geefBeschikbareKleuren() {
		return huidigSpel.geefBeschikbareKleuren();
	}

	// public SpelerDTO geefSpelerAanbeurt() {}

}
