package domein;

import java.util.ArrayList;

import DTO.spelerDTO;

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

	public void spelerDoetMee(spelerDTO speler, Kleur kleur) {
		if (spelerRepository.bestaatSpeler(speler.gebruikersnaam())) {
			Speler deelNemendeSpeler = spelerRepository.geefSpeler(speler.gebruikersnaam());
			deelNemendeSpeler.setKleur(kleur);
			
			deelnemendeSpelers.add(deelNemendeSpeler);
		} else {
			//throw new IllegalArgumentException();
		}
	}

	public void startSpel() {
		// Nakijken of deelnemendeSpelers 3 of 4 zijn en of ze kleuren hebben

		huidigSpel = new Spel(deelnemendeSpelers);
	}

	// Geeft een lijst van spelerDTO's terug
	public ArrayList<spelerDTO> geefBeschikbareSpelers() {
		ArrayList<spelerDTO> beschikbareSpelersDTO = new ArrayList<>();
		ArrayList<Speler> beschikbareSpelers = spelerRepository.geefLijstBestaandeSpelers();
		
		if (!deelnemendeSpelers.isEmpty())
			beschikbareSpelers.removeAll(deelnemendeSpelers);
		
		for (Speler speler : beschikbareSpelers) {
			beschikbareSpelersDTO.add(new spelerDTO(speler.getGebruikersnaam(), speler.getGeboortejaar(), speler.getAantalGewonnen(), speler.getAantalGespeeld()));
		}
		
		return beschikbareSpelersDTO;
	}

	// Geeft de beschikbare kleuren terug
		public ArrayList<Kleur> geefBeschikbareKleuren() {
			ArrayList<Kleur> gebruikteKleuren = new ArrayList<>();
			ArrayList<Kleur> nietGebruikteKleuren = new ArrayList<>();

			for (Speler speler : deelnemendeSpelers) {
				gebruikteKleuren.add(speler.getKleur());
			}

			for (Kleur kleur : Kleur.values()) {
				if (!gebruikteKleuren.contains(kleur))
					nietGebruikteKleuren.add(kleur);
			}

			return nietGebruikteKleuren;
		}

	public boolean bestaatSpeler(String gebruikersnaam) {
		return spelerRepository.bestaatSpeler(gebruikersnaam);
	}

	public spelerDTO geefSpelerAanbeurt() {
		if (huidigSpel == null) {
			// throw new IllegalArgumentException();
		}
		Speler koning = huidigSpel.getKoning();
		return new spelerDTO(koning.getGebruikersnaam(), koning.getGeboortejaar(), koning.getAantalGewonnen(), koning.getAantalGespeeld());
	}
	
	private void haalSpelersOp() {
		
	}
}
