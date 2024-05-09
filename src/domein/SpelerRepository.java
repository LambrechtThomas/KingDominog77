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

	// Check bestaatSpeler: kijkt of de speler(gebruikersnaam) al bestaat, zo ja
	// throw exception
	public void voegToe(Speler speler) {
		if (bestaatSpeler(speler.getGebruikersnaam()))
			throw new GebruikersnaamInGebruikException();

		mapper.voegToe(speler);
	}

	public boolean bestaatSpeler(String gebruikersnaam) {
		return mapper.geefSpeler(gebruikersnaam) != null;
	}

	public Speler geefSpeler(String gebruikersnaam) {
		return mapper.geefSpeler(gebruikersnaam);
	}

	public ArrayList<Speler> geefLijstBestaandeSpelers() {
		return mapper.geefSpelers();
	}

	public void verhoogVeldenGames(List<Speler> spelers, Speler speler) {
		mapper.verhoogVelden(spelers, speler);
	}
}
