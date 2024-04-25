package domein;

import java.util.ArrayList;

import DTO.dominoTegelDTO;
import DTO.spelerDTO;

public class DomeinController {

	private final SpelerRepository spelerRepository;
	private final DominoTegelRepository dominoRepo;
	private Spel huidigSpel;

	private ArrayList<Speler> deelnemendeSpelers;
	private ArrayList<Speler> beschikbareSpelers;

	public DomeinController() {
		spelerRepository = new SpelerRepository();
		dominoRepo = new DominoTegelRepository();

		deelnemendeSpelers = new ArrayList<>();
		beschikbareSpelers = spelerRepository.geefLijstBestaandeSpelers();
	}

	public void registreerSpeler(String gebruikersnaam, int geboortejaar) {
		Speler nieuweSpeler = new Speler(gebruikersnaam, geboortejaar);
		spelerRepository.voegToe(nieuweSpeler);
		beschikbareSpelers = spelerRepository.geefLijstBestaandeSpelers();
	}

	public void spelerDoetMee(spelerDTO speler, Kleur kleur) {
		if (spelerRepository.bestaatSpeler(speler.gebruikersnaam())) {
			Speler deelNemendeSpeler = spelerRepository.geefSpeler(speler.gebruikersnaam());
			deelNemendeSpeler.setKleur(kleur);

			deelnemendeSpelers.add(deelNemendeSpeler);
		} else {
			// throw new IllegalArgumentException();
		}
	}

	public void startSpel() {
		// Nakijken of deelnemendeSpelers 3 of 4 zijn en of ze kleuren hebben

		huidigSpel = new Spel(deelnemendeSpelers, dominoRepo.geefLijstDominos(deelnemendeSpelers.size()));
	}

	public boolean isSpelTenEinde() {
		if (huidigSpel.getRonde() == 13)
			return true;

		return false;
	}

	public int geefAantalSpelers() {
		return huidigSpel.getHuidigeSpelers().size();
	}

	public ArrayList<spelerDTO> geefDeelnemendeSpelers() {
		ArrayList<Speler> spelers = huidigSpel.getHuidigeSpelers();
		ArrayList<spelerDTO> spelersDTO = new ArrayList<>();

		for (Speler speler : spelers) {
			spelersDTO.add(new spelerDTO(speler.getGebruikersnaam(), speler.getGeboortejaar(),
					speler.getAantalGewonnen(), speler.getAantalGespeeld()));
		}

		return spelersDTO;
	}

	// Geeft een lijst van spelerDTO's terug
	public ArrayList<spelerDTO> geefBeschikbareSpelers() {
		ArrayList<spelerDTO> beschikbareSpelersDTO = new ArrayList<>();

		if (!deelnemendeSpelers.isEmpty())
			beschikbareSpelers.removeAll(deelnemendeSpelers);

		for (Speler speler : beschikbareSpelers) {
			beschikbareSpelersDTO.add(new spelerDTO(speler.getGebruikersnaam(), speler.getGeboortejaar(),
					speler.getAantalGewonnen(), speler.getAantalGespeeld()));
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

	// Checked of speler bestaat
	public boolean bestaatSpeler(String gebruikersnaam) {
		return spelerRepository.bestaatSpeler(gebruikersnaam);
	}

	// Geeft het eerste kolom terug
	public ArrayList<dominoTegelDTO> geefStartKolom() {
		ArrayList<dominoTegelDTO> startKolom = new ArrayList<>();
		ArrayList<DominoTegel> startKolomDomino = huidigSpel.getStartKolom();

		for (DominoTegel d : startKolomDomino) {
			startKolom.add(new dominoTegelDTO(d.getVolgnummer(), d.getTegels(), d.isHorizontaal(), d.isSpiegeld()));
		}

		return startKolom;
	}

	// Geeft de speler terug die aan de beurt is
	public spelerDTO geefKoning() {
		if (huidigSpel == null) {
			// throw new IllegalArgumentException();
		}
		Speler koning = huidigSpel.getKoning();
		return new spelerDTO(koning.getGebruikersnaam(), koning.getGeboortejaar(), koning.getAantalGewonnen(),
				koning.getAantalGespeeld());
	}

	// Kiest een nieuwe koning
	public void kiesNieuweKoning() {
		huidigSpel.kiesKoning();
	}

	public int getRonde() {
		return huidigSpel.getRonde();
	}
}
