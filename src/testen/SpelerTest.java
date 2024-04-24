package testen;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import domein.Speler;

class SpelerTest {
	private Speler speler;

	// ------------------------------------------------------------------
	// Alle gegevens moeten correct zijn. -------------------------------

	@ParameterizedTest
	@ValueSource(ints = { 2005, 2007, 2009 })
	void speler_GeldigeLeeftijd_asserEquals(int geboortejaar) {
		speler = new Speler("avatar", geboortejaar, 4, 25);
		Assertions.assertEquals("avatar", speler.getGebruikersnaam());
		Assertions.assertEquals(geboortejaar, speler.getGeboortejaar());
		Assertions.assertEquals(4, speler.getAantalGewonnen());
		Assertions.assertEquals(25, speler.getAantalGespeeld());
	}

	@Test
	void maakSpeler_alleGegevensCorrect_maaktObject() {
		speler = new Speler("avatar", 2003, 4, 25);
		Assertions.assertEquals("avatar", speler.getGebruikersnaam());
		Assertions.assertEquals(2003, speler.getGeboortejaar());
		Assertions.assertEquals(4, speler.getAantalGewonnen());
		Assertions.assertEquals(25, speler.getAantalGespeeld());
	}

	// ------------------------------------------------------------------
	// Alle gegevens moeten fout zijn. ----------------------------------
	@ParameterizedTest
	@NullAndEmptySource
	@ValueSource(strings = { "      " })
	void speler_OngeldigeNaam_assertThrow(String naam) {
		assertThrows(IllegalArgumentException.class, () -> new Speler(naam, 2005, 4, 25));
	}

	@ParameterizedTest
	@ValueSource(ints = { 0001, 2025, 2101 })
	void speler_OngeldigeLeeftijd_assertThrow(int geboortejaar) {
		assertThrows(IllegalArgumentException.class, () -> new Speler("avatar", geboortejaar, 4, 25));
	}

	@ParameterizedTest
	@ValueSource(ints = { -100, -10, -1 })
	void speler_NegatiefAantalGewonnen_assertThrow(int negatiefGewonnen) {
		assertThrows(IllegalArgumentException.class, () -> new Speler("avatar", 2005, negatiefGewonnen, 25));
	}

	@ParameterizedTest
	@ValueSource(ints = { -100, -10, -1 })
	void speler_NegatiefaantalGespeelt_assertThrow(int negatiefGespeeld) {
		assertThrows(IllegalArgumentException.class, () -> new Speler("avatar", 2005, 4, negatiefGespeeld));
	}

	//

	@Test
	void speler_geenKleur_assertThrow() {
		Speler speler = new Speler("avatar", 2005, 4, 5);
		assertThrows(IllegalArgumentException.class, () -> speler.setKleur(null));
	}
}
