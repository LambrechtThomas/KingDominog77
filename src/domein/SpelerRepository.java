package domein;

import java.util.ArrayList;
import java.util.List;

import exceptions.GebruikersnaamInGebruikException;
import persistentie.SpelerMapper;

public class SpelerRepository {

	private final SpelerMapper mapper;

	public SpelerRepository() {
		mapper = new SpelerMapper();
	}

	/**
	 * Checkt of de speler al bestaat, zoja ==> throw exception
	 * 
	 * @param speler
	 */
	public void voegToe(Speler speler) {
		if (bestaatSpeler(speler.getGebruikersnaam()))
			throw new GebruikersnaamInGebruikException();

		mapper.voegToe(speler);
	}

	/**
	 * Checkt of de speler bestaat
	 * 
	 * @param gebruikersnaam
	 * @return true/false
	 */
	public boolean bestaatSpeler(String gebruikersnaam) {
		return mapper.geefSpeler(gebruikersnaam) != null;
	}

	/**
	 * Geeft de speler
	 * 
	 * @param gebruikersnaam
	 * @return speler
	 */
	public Speler geefSpeler(String gebruikersnaam) {
		return mapper.geefSpeler(gebruikersnaam);
	}

	/**
	 * Geeft lijst met bestaande spelers
	 * 
	 * @return lijst met bestaande spelers
	 */
	public ArrayList<Speler> geefLijstBestaandeSpelers() {
		return mapper.geefSpelers();
	}

	/**
	 * Roept methode aan die velden van gepseelde games en gewonnen game verhoogd
	 * 
	 * @param spelers
	 * @param speler
	 */
	public void verhoogVeldenGames(List<Speler> spelers, Speler speler) {
		mapper.verhoogVelden(spelers, speler);
	}
}
