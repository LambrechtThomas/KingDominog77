package domein;

import java.util.ArrayList;
import java.util.stream.Collectors;

import DTO.dominoTegelDTO;
import DTO.spelerDTO;
import exceptions.GebruikersnaamInGebruikException;
import exceptions.SpelBestaatNietException;
import exceptions.SpelerDoetAlMeeException;
import persistentie.SpelerMapper;

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

	// registeert een speler in de databank
	public void registreerSpeler(String gebruikersnaam, int geboortejaar) {
		checkVoorGebruikersNaam(gebruikersnaam);
		checkAlsSpelerAlMeeDoet(gebruikersnaam);

		Speler nieuweSpeler = new Speler(gebruikersnaam, geboortejaar);
		spelerRepository.voegToe(nieuweSpeler);
		beschikbareSpelers = spelerRepository.geefLijstBestaandeSpelers();
	}

	// Voeg een speler toe aan de huidige deelname lijst (spel is nog niet gemaakt)
	public void spelerDoetMee(spelerDTO speler, Kleur kleur) {
		checkOfSpelerNietBestaat(speler);

		Speler deelNemendeSpeler = spelerRepository.geefSpeler(speler.gebruikersnaam());
		deelNemendeSpeler.setKleur(kleur);

		deelnemendeSpelers.add(deelNemendeSpeler);

	}

	public void startSpel() {
		CheckOfSpelKlaarIsGezet();

		huidigSpel = new Spel(deelnemendeSpelers, dominoRepo.geefLijstDominos(deelnemendeSpelers.size()));
	}

	// Checked of spel gedaan is (spel kan nooit verder dan ronde 13 gaan)
	public boolean isSpelTenEinde() {
		checkVoorHuidigSpel();

		if (huidigSpel.getRonde() == 13)
			return true;

		return false;
	}

	// Geeft weer hoeveel spelers er spelen
	public int geefAantalSpelers() {
		checkVoorHuidigSpel();

		return huidigSpel.getHuidigeSpelers().size();
	}

	// Deze geeft een lijst terug van spelerDTO, deze spelers zitten in spel
	public ArrayList<spelerDTO> geefDeelnemendeSpelers() {
		checkVoorHuidigSpel();

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
		checkVoorHuidigSpel();

		ArrayList<dominoTegelDTO> startKolom = new ArrayList<>();
		ArrayList<DominoTegel> startKolomDomino = huidigSpel.getStartKolom();

		for (DominoTegel d : startKolomDomino) {
			startKolom.add(new dominoTegelDTO(d.getVolgnummer(), d.getTegels(), d.isHorizontaal(), d.isSpiegeld()));
		}

		return startKolom;
	}

	// Geeft de speler terug die aan de beurt is
	public spelerDTO geefKoning() {
		checkVoorHuidigSpel();

		Speler koning = huidigSpel.getKoning();
		return new spelerDTO(koning.getGebruikersnaam(), koning.getGeboortejaar(), koning.getAantalGewonnen(),
				koning.getAantalGespeeld());
	}

	// Kiest een nieuwe koning
	public void kiesNieuweKoning() {
		checkVoorHuidigSpel();

		huidigSpel.kiesKoning();
	}

	// Vraagt aan spel welke ronde we zitten
	public int getRonde() {
		checkVoorHuidigSpel();

		return huidigSpel.getRonde();
	}

	// === Checks ===
	private void checkVoorHuidigSpel() {
		if (huidigSpel == null)
			throw new SpelBestaatNietException();
	}

	private void checkVoorGebruikersNaam(String naam) {
		if (spelerRepository.bestaatSpeler(naam))
			throw new GebruikersnaamInGebruikException();
	}

	private void checkOfSpelerNietBestaat(spelerDTO speler) {
		if (!spelerRepository.bestaatSpeler(speler.gebruikersnaam()))
			throw new SpelBestaatNietException();
	}
	
	private void checkAlsSpelerAlMeeDoet(String gebruikersnaam) {
		ArrayList<String> deelname = (ArrayList<String>) deelnemendeSpelers.stream().map(v -> v.getGebruikersnaam()).collect(Collectors.toList());
		if (deelname.contains(gebruikersnaam))
			throw new SpelerDoetAlMeeException();
		
	}
	
	private void CheckOfSpelKlaarIsGezet() {
		if (deelnemendeSpelers.size() < 3 || deelnemendeSpelers.size() > 4)
			throw new IllegalArgumentException();
		
		ArrayList<Kleur> kleuren = (ArrayList<Kleur>) deelnemendeSpelers.stream().map(v -> v.getKleur()).distinct().collect(Collectors.toList());
		
		if (kleuren.size() != deelnemendeSpelers.size())
			throw new IllegalArgumentException();
	}
}
