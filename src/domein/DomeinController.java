package domein;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import DTO.dominoTegelDTO;
import DTO.spelerDTO;
import DTO.tegelDTO;
import exceptions.GebruikersnaamInGebruikException;
import exceptions.KleurBestaatNietException;
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
	 * Geeft alle deelnemende spelers terug
	 * 
	 * @return speler
	 */
	public ArrayList<spelerDTO> getDeelnemendeSpelers() {
		ArrayList<spelerDTO> spelersDTO = new ArrayList<>();

		for (Speler speler : deelnemendeSpelers) {
			spelersDTO.add(new spelerDTO(speler.getGebruikersnaam(), speler.getGeboortejaar(), speler.getTotaleScore(),
					speler.getAantalGewonnen(), speler.getAantalGespeeld(), speler.getKleur()));
		}

		return spelersDTO;
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
		checkOfKleurBestaat(kleur);

		Speler gekozenSpeler = beschikbareSpelers.stream()
				.filter(v -> v.getGebruikersnaam().equals(speler.gebruikersnaam())).findFirst().get();
		gekozenSpeler.setKleur(kleur);
		deelnemendeSpelers.add(gekozenSpeler);
	}

	/**
	 * Verwijder speler uit deelnemendeSpers, de speler doet niet meer mee aan het
	 * spel
	 * 
	 * @param speler
	 * @param kleur
	 * @throws Exception
	 */
	public void spelerDoetNietMeer(spelerDTO speler, Kleur kleur) throws Exception {
		checkOfSpelerNietBestaat(speler);

		Speler verwijderSpeler = beschikbareSpelers.stream()
				.filter(v -> v.getGebruikersnaam().equals(speler.gebruikersnaam())).findFirst().get();
		verwijderSpeler.setKleur(null);
		deelnemendeSpelers.remove(verwijderSpeler);
	}

	/**
	 * Alle deelneemende spelers verwijderen uit de lijst
	 */
	public void clearDeelnemedeSpeler() {
		deelnemendeSpelers.clear();
	}

	/**
	 * Start het spel
	 * 
	 * @throws Exception
	 */
	public void startSpel() throws Exception {
		checkOfSpelKlaarIsGezet();

		huidigSpel = new Spel(deelnemendeSpelers, dominoRepo.geefLijstDominos(deelnemendeSpelers.size()));
	}

	// Checked of spel gedaan is (spel kan nooit verder dan ronde 13 gaan)

	/**
	 * Checkt of er 13 rondes gespeeld zijn
	 * 
	 * @return true/false
	 * @throws Exception
	 */
	public boolean isSpelTenEinde() throws Exception {
		checkVoorHuidigSpel();

		if (huidigSpel.getRonde() == 13)
			return true;

		return false;
	}

	/**
	 * Geeft weer hoeveel spelers in het spel zitten
	 * 
	 * @return aantal spelers
	 */
	public int geefAantalSpelers() {
		return deelnemendeSpelers.size();
	}

	// Deze geeft een lijst terug van spelerDTO, deze spelers zitten in spel

	/**
	 * Geeft een lijst met alle deelnemende spelers in het spel weer
	 * 
	 * @return alle deelnemende spelers
	 */
	public ArrayList<spelerDTO> geefDeelnemendeSpelersInSpel() throws Exception {
		checkVoorHuidigSpel();

		ArrayList<Speler> spelers = huidigSpel.getHuidigeSpelers();
		ArrayList<spelerDTO> spelersDTO = new ArrayList<>();

		for (Speler speler : spelers) {
			spelersDTO.add(new spelerDTO(speler.getGebruikersnaam(), speler.getGeboortejaar(), speler.getTotaleScore(),
					speler.getAantalGewonnen(), speler.getAantalGespeeld(), speler.getKleur()));
		}

		return spelersDTO;
	}

	/**
	 * Geeft de spelers/gebruikers terug die nog niet in een game zitten (aka ze
	 * zijn nog beschikbaar)
	 * 
	 * @return alle gebruikers die nog beschikbaar zijn
	 */
	public ArrayList<spelerDTO> geefBeschikbareSpelers() {
		ArrayList<spelerDTO> beschikbareSpelersDTO = new ArrayList<>();
		ArrayList<Speler> overgeblevenspelers = beschikbareSpelers.stream().filter(v -> !deelnemendeSpelers.contains(v))
				.collect(Collectors.toCollection(ArrayList::new));

		for (Speler speler : overgeblevenspelers) {
			beschikbareSpelersDTO
					.add(new spelerDTO(speler.getGebruikersnaam(), speler.getGeboortejaar(), speler.getTotaleScore(),
							speler.getAantalGewonnen(), speler.getAantalGespeeld(), speler.getKleur()));
		}

		return beschikbareSpelersDTO;
	}

	/**
	 * Geeft de kleuren weer die nog gekozen kunnen worden
	 * 
	 * @return Geeft de beschikbare kleuren terug
	 */
	public ArrayList<Kleur> geefBeschikbareKleuren() {
		ArrayList<Kleur> gebruikteKleuren = new ArrayList<>();
		ArrayList<Kleur> nietGebruikteKleuren = new ArrayList<>();

		for (Speler speler : deelnemendeSpelers) {
			gebruikteKleuren.add(speler.getKleur());
		}

		for (Kleur kleur : Kleur.values()) {
			if (!gebruikteKleuren.contains(kleur)) {
				nietGebruikteKleuren.add(kleur);
			}
		}

		return nietGebruikteKleuren;
	}

	/**
	 * Checkt of de gebruikersnaam al bestaat in de repo
	 * 
	 * @param gebruikersnaam
	 * @return true/false
	 */
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

	/**
	 * Plaatst domino bij koning
	 * 
	 * @param dominoDTO
	 * @param speler
	 * @param rij
	 * @param kolom
	 * @throws Exception
	 */
	public void plaatsDomino(dominoTegelDTO dominoDTO, spelerDTO speler, int rij, int kolom) throws Exception {
		if (speler.gebruikersnaam().equals(huidigSpel.getKoning().getGebruikersnaam())) {
			huidigSpel.plaatsDominoTegel(dominoDTO.volgnummer(), rij, kolom);
		} else
			throw new IllegalArgumentException("spelers komen niet overeen!!");
	}

	/**
	 * Geeft de koning weer
	 * 
	 * @return koning
	 * @throws Exception
	 */
	public spelerDTO geefKoning() throws Exception {
		checkVoorHuidigSpel();

		Speler koning = huidigSpel.getKoning();
		return new spelerDTO(koning.getGebruikersnaam(), koning.getGeboortejaar(), koning.getAantalGewonnen(),
				koning.getTotaleScore(), koning.getAantalGespeeld(), koning.getKleur());
	}

	/**
	 * Kiest nieuwe koning
	 * 
	 * @throws Exception
	 */
	public void kiesNieuweKoning() throws Exception {
		checkVoorHuidigSpel();

		huidigSpel.kiesKoning();
	}

	/**
	 * Vraagt wat de huidige ronde is
	 * 
	 * @return huidige ronde
	 * @throws Exception
	 */
	public int getRonde() throws Exception {
		checkVoorHuidigSpel();

		return huidigSpel.getRonde();
	}

	/**
	 * Checkt of het spel is klaargezet
	 * 
	 * @return true/false
	 */
	public boolean isSpelKlaarGezet() {
		if (deelnemendeSpelers.size() >= MIN_AANTAL_SPELERS && deelnemendeSpelers.size() <= MAX_AANTAL_SPELERS
				&& huidigSpel == null) {
			return true;
		}

		return false;
	}

	/**
	 * Wisselt kolom
	 * 
	 * @throws Exception
	 */
	public void wisselKolom() throws Exception {
		checkVoorHuidigSpel();
		huidigSpel.wisselKolomTegel();
	}

	public void updateDataBase() throws Exception {
		checkVoorHuidigSpel();
		if (isSpelTenEinde()) {
			spelerRepository.verhoogVeldenGames(huidigSpel.getHuidigeSpelers(), huidigSpel.geefWinnaar());
		}
	}

	public void draaiDomino(dominoTegelDTO domino) throws Exception {
		checkVoorHuidigSpel();

		huidigSpel.draaiDomino(domino.volgnummer());
	}

	public void spiegelDomino(dominoTegelDTO domino) throws Exception {
		checkVoorHuidigSpel();

		huidigSpel.spiegelDomino(domino.volgnummer());
	}

	public spelerDTO geefWinnaar() throws Exception {
		checkVoorHuidigSpel();

		Speler s = huidigSpel.geefWinnaar();
		return new spelerDTO(s.getGebruikersnaam(), s.getGeboortejaar(), s.getTotaleScore(), s.getAantalGewonnen(),
				s.getAantalGespeeld(), s.getKleur());
	}

	// === Checks ===

	/**
	 * Checkt of het huidige spel bestaat
	 * 
	 * @throws Exception
	 */
	private void checkVoorHuidigSpel() throws Exception {
		if (huidigSpel == null)
			throw new SpelBestaatNietException();
	}

	/**
	 * Checkt of de gebruikersnaam al dan niet al in gebruik is
	 * 
	 * @param naam
	 * @throws Exception
	 */
	private void checkVoorGebruikersNaam(String naam) throws Exception {
		if (spelerRepository.bestaatSpeler(naam))
			throw new GebruikersnaamInGebruikException();
	}

	/**
	 * Checkt of de speler bestaat of niet
	 * 
	 * @param speler
	 * @throws Exception
	 */
	private void checkOfSpelerNietBestaat(spelerDTO speler) throws Exception {
		if (!spelerRepository.bestaatSpeler(speler.gebruikersnaam()))
			throw new SpelerBestaatNietException();
	}

	/**
	 * Checkt of de speler meedoet aan de game of niet
	 * 
	 * @param speler
	 * @throws Exception
	 */
	private void checkAlsSpelerAlMeeDoet(spelerDTO speler) throws Exception {
		ArrayList<String> deelname = (ArrayList<String>) deelnemendeSpelers.stream().map(v -> v.getGebruikersnaam())
				.collect(Collectors.toList());
		if (deelname.contains(speler.gebruikersnaam()))
			throw new SpelerDoetAlMeeException();

	}

	/**
	 * Checkt of het spel is klaargezet
	 * 
	 * @throws Exception
	 */
	private void checkOfSpelKlaarIsGezet() throws Exception {
		if (deelnemendeSpelers.size() < MIN_AANTAL_SPELERS || deelnemendeSpelers.size() > MAX_AANTAL_SPELERS)
			throw new IllegalArgumentException();

		ArrayList<Kleur> kleuren = (ArrayList<Kleur>) deelnemendeSpelers.stream().map(v -> v.getKleur()).distinct()
				.collect(Collectors.toList());

		if (kleuren.size() != deelnemendeSpelers.size())
			throw new IllegalArgumentException();
	}

	/**
	 * Checkt of de kleur al dan niet bestaat in onze game
	 * 
	 * @param kleur
	 */
	private void checkOfKleurBestaat(Kleur kleur) {
		ArrayList<Kleur> kleuren = new ArrayList<>(Arrays.asList(Kleur.values()));
		if (!kleuren.contains(kleur)) {
			throw new KleurBestaatNietException();
		}
	}

	// GUI - Alertbox oproepen

	/**
	 * De alert die in GUI tevoorschijn komt bij fouten
	 * 
	 * @param infoMessage
	 * @param titleBar
	 * @param headerMessage
	 */
	public static void errorBox(String infoMessage, String titleBar, String headerMessage) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle(titleBar);
		alert.setHeaderText(headerMessage);
		alert.setContentText(infoMessage);
		alert.showAndWait();
	}

	/**
	 * De alert die in GUI tevoorschijn komt als een actie gelukt is
	 * 
	 * @param infoMessage
	 * @param titleBar
	 * @param headerMessage
	 */
	public static void doneBox(String infoMessage, String titleBar, String headerMessage) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(titleBar);
		alert.setHeaderText(headerMessage);
		alert.setContentText(infoMessage);
		alert.showAndWait();
	}

	/**
	 * Een alert die in de GUI tevoorschijn komt om info mee te delen
	 * 
	 * @param infoMessage
	 * @param titleBar
	 * @param headerMessage
	 */
	public static void infoBox(String infoMessage, String titleBar, String headerMessage) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle(titleBar);
		alert.setHeaderText(headerMessage);
		alert.setContentText(infoMessage);
		alert.showAndWait();
	}

}
