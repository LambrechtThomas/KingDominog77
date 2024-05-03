package domein;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

import DTO.dominoTegelDTO;
import DTO.spelerDTO;
import DTO.tegelDTO;
import exceptions.GebruikersnaamInGebruikException;
import exceptions.SpelBestaatNietException;
import exceptions.SpelerBestaatNietException;
import exceptions.SpelerDoetAlMeeException;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class DomeinController {

	private final SpelerRepository spelerRepository;
	private final DominoTegelRepository dominoRepo;
	private Spel huidigSpel;

	private ArrayList<Speler> deelnemendeSpelers;
	private ArrayList<Speler> beschikbareSpelers;

	private static final int MIN_AANTAL_SPELERS = 3;
	private static final int MAX_AANTAL_SPELERS = 4;

	public DomeinController() {
		spelerRepository = new SpelerRepository();
		dominoRepo = new DominoTegelRepository();

		deelnemendeSpelers = new ArrayList<>();
		beschikbareSpelers = spelerRepository.geefLijstBestaandeSpelers();
	}

	/**
	 * Speler registreren door new Speler en dan parmeters (naam, geboortejaar) mee
	 * te geven. Daarna toevoegen aan spelerRepository
	 * 
	 * @param gebruikersnaam naam speler
	 * @param geboortejaar   geboortejaar speler
	 */

	public void registreerSpeler(String gebruikersnaam, int geboortejaar) throws Exception {
		checkVoorGebruikersNaam(gebruikersnaam);

		Speler nieuweSpeler = new Speler(gebruikersnaam, geboortejaar);
		spelerRepository.voegToe(nieuweSpeler);
		beschikbareSpelers = spelerRepository.geefLijstBestaandeSpelers();
	}

	/**
	 * Speler uit de repository halen als deze meedoet aan het spel. Daarna speler
	 * een kleur toewijzen
	 * 
	 * @param speler (gebruikersnaam, geboortejaar) van de gebruiker
	 * @param kleur  Kleur van de Koning
	 */

	public void spelerDoetMee(spelerDTO speler, Kleur kleur) throws Exception {
		checkOfSpelerNietBestaat(speler);
		checkAlsSpelerAlMeeDoet(speler);

		Speler deelNemendeSpeler = beschikbareSpelers.stream()
				.filter(v -> v.getGebruikersnaam().equals(speler.gebruikersnaam())).findFirst().get();
		deelNemendeSpeler.setKleur(kleur);

		deelnemendeSpelers.add(deelNemendeSpeler);

	}
	
	public void registreerGeselecteerdeSpelers(ArrayList<spelerDTO> geselecteerdeSpelers) throws Exception {
	    Random random = new Random();
	    for (spelerDTO speler : geselecteerdeSpelers) {
	        int randomKleurIndex = random.nextInt(4);
	        Kleur willekeurigeKleur = Kleur.values()[randomKleurIndex];
	        spelerDoetMee(speler, willekeurigeKleur);
	    }
	}
	
	public void printDeelnemendeSpelers() {
	    for (Speler speler : deelnemendeSpelers) {
	        System.out.println(speler.getGebruikersnaam() + ", " + speler.getKleur());
	    }
	}

	

	public void clearDeelnemedeSpeler() {
		deelnemendeSpelers.clear();
	}

	public void startSpel() throws Exception {
		CheckOfSpelKlaarIsGezet();

		huidigSpel = new Spel(deelnemendeSpelers, dominoRepo.geefLijstDominos(deelnemendeSpelers.size()));
	}

	// Checked of spel gedaan is (spel kan nooit verder dan ronde 13 gaan)

	// check of er 13 rondes gespeeld zijn en eindig dan het spel

	public boolean isSpelTenEinde() throws Exception {
		checkVoorHuidigSpel();

		if (huidigSpel.getRonde() == 13)
			return true;

		return false;
	}

	// Geeft weer hoeveel spelers er spelen

	public int geefAantalSpelers() {
		return deelnemendeSpelers.size();
	}

	// Deze geeft een lijst terug van spelerDTO, deze spelers zitten in spel

	/**
	 * Maak een spelerDTO aan voor elke speler. Voeg deze spelerDTO toe aan
	 * spelersDTO
	 * 
	 * @return een arraylist spelersDTO
	 */

	public ArrayList<spelerDTO> geefDeelnemendeSpelersInSpel() throws Exception {
		checkVoorHuidigSpel();

		ArrayList<Speler> spelers = huidigSpel.getHuidigeSpelers();
		ArrayList<spelerDTO> spelersDTO = new ArrayList<>();

		for (Speler speler : spelers) {
			spelersDTO.add(new spelerDTO(speler.getGebruikersnaam(), speler.getGeboortejaar(), speler.getTotaleScore(),
					speler.getAantalGewonnen(), speler.getAantalGespeeld()));
		}

		return spelersDTO;
	}

	/**
	 * Als er beschikbare spelers zijn, verwijder dan alle deelnemende spelers uit
	 * beschikbare spelers Maak een spelerDTO aan voor elke speler in het actief
	 * spel. Voeg deze spelerDTO toe aan beschikbareSpelersDTO
	 * 
	 * @return Geeft een lijst van spelerDTO's terug
	 */
	public ArrayList<spelerDTO> geefBeschikbareSpelers() {
		ArrayList<spelerDTO> beschikbareSpelersDTO = new ArrayList<>();
		beschikbareSpelers.removeAll(deelnemendeSpelers);

		for (Speler speler : beschikbareSpelers) {
			beschikbareSpelersDTO.add(new spelerDTO(speler.getGebruikersnaam(), speler.getGeboortejaar(),
					speler.getTotaleScore(), speler.getAantalGewonnen(), speler.getAantalGespeeld()));
		}

		return beschikbareSpelersDTO;
	}

	/**
	 * Voeg elke gebruikte kleur toe aan een arraylist Voeg elke niet gebruikte
	 * kleur toe aan een arraylist afhv gebruikteKleuren
	 * 
	 * @return Geeft de niet gebruikte kleuren terug
	 */
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

	// Checked of de gebruikersnaam al bestaat in de spelerRepository
	public boolean bestaatSpeler(String gebruikersnaam) {
		return spelerRepository.bestaatSpeler(gebruikersnaam);
	}

	/**
	 * Zet de startkolom om in een arraylist van dominotegelDTO's
	 * 
	 * @return geeft de startkolom
	 */
	public ArrayList<dominoTegelDTO> geefStartKolom() throws Exception {
		checkVoorHuidigSpel();

		ArrayList<dominoTegelDTO> startKolom = new ArrayList<>();
		ArrayList<DominoTegel> startKolomDomino = huidigSpel.getStartKolom();

		for (DominoTegel d : startKolomDomino) {
			tegelDTO[] tegels = new tegelDTO[2];

			for (int i = 0; i < d.getTegels().length; i++) {
				Tegel t = d.getTegels()[i];
				tegels[i] = new tegelDTO(t.getLandschap(), t.getKroontjes());
			}

			startKolom.add(new dominoTegelDTO(d.getVolgnummer(), tegels, d.isHorizontaal(), d.isSpiegeld()));
		}

		return startKolom;
	}

	// plaats domino bij koning
	public void plaatsDomino(dominoTegelDTO dominoDTO, spelerDTO speler, int rij, int kolom) throws Exception {
		DominoTegel domino = huidigSpel.getStartKolom().stream()
				.filter(v -> v.getVolgnummer() == dominoDTO.volgnummer()).findFirst().get();
		if (speler.gebruikersnaam().equals(huidigSpel.getKoning().getGebruikersnaam())) {
			huidigSpel.plaatsDominoTegel(domino, rij, kolom);
		} else
			throw new IllegalArgumentException("spelers komen niet overeen!!");
	}

	// Geeft de speler terug die aan de beurt is
	public spelerDTO geefKoning() throws Exception {
		checkVoorHuidigSpel();

		Speler koning = huidigSpel.getKoning();
		return new spelerDTO(koning.getGebruikersnaam(), koning.getGeboortejaar(), koning.getAantalGewonnen(),
				koning.getTotaleScore(), koning.getAantalGespeeld());
	}

	// Kiest een nieuwe koning
	public void kiesNieuweKoning() throws Exception {
		checkVoorHuidigSpel();

		huidigSpel.kiesKoning();
	}

	// Vraagt aan spel welke ronde we zitten

	public int getRonde() throws Exception {
		checkVoorHuidigSpel();

		return huidigSpel.getRonde();
	}

	// Deze methode zeg of het spel correct is klaar gezet (spel bestaat nog niet)
	public boolean isSpelKlaarGezet() {
		if (deelnemendeSpelers.size() >= MIN_AANTAL_SPELERS && deelnemendeSpelers.size() <= MAX_AANTAL_SPELERS
				&& huidigSpel == null) {
			return true;
		}

		return false;
	}

	// === Checks ===
	private void checkVoorHuidigSpel() throws Exception {
		if (huidigSpel == null)
			throw new SpelBestaatNietException();
	}

	private void checkVoorGebruikersNaam(String naam) throws Exception {
		if (spelerRepository.bestaatSpeler(naam))
			throw new GebruikersnaamInGebruikException();
	}

	private void checkOfSpelerNietBestaat(spelerDTO speler) throws Exception {
		if (!spelerRepository.bestaatSpeler(speler.gebruikersnaam()))
			throw new SpelerBestaatNietException();
	}

	private void checkAlsSpelerAlMeeDoet(spelerDTO speler) throws Exception {
		ArrayList<String> deelname = (ArrayList<String>) deelnemendeSpelers.stream().map(v -> v.getGebruikersnaam())
				.collect(Collectors.toList());
		if (deelname.contains(speler.gebruikersnaam()))
			throw new SpelerDoetAlMeeException();

	}

	private void CheckOfSpelKlaarIsGezet() throws Exception {
		if (deelnemendeSpelers.size() < MIN_AANTAL_SPELERS || deelnemendeSpelers.size() > MAX_AANTAL_SPELERS)
			throw new IllegalArgumentException();

		ArrayList<Kleur> kleuren = (ArrayList<Kleur>) deelnemendeSpelers.stream().map(v -> v.getKleur()).distinct()
				.collect(Collectors.toList());

		if (kleuren.size() != deelnemendeSpelers.size())
			throw new IllegalArgumentException();
	}

	// GUI - Alertbox oproepen
	public static void errorBox(String infoMessage, String titleBar, String headerMessage) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle(titleBar);
		alert.setHeaderText(headerMessage);
		alert.setContentText(infoMessage);
		alert.showAndWait();
	}

	public static void doneBox(String infoMessage, String titleBar, String headerMessage) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(titleBar);
		alert.setHeaderText(headerMessage);
		alert.setContentText(infoMessage);
		alert.showAndWait();
	}

	public static void infoBox(String infoMessage, String titleBar, String headerMessage) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle(titleBar);
		alert.setHeaderText(headerMessage);
		alert.setContentText(infoMessage);
		alert.showAndWait();
	}

}
