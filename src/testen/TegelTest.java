package testen;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import domein.Tegel;

class TegelTest {

	// ------------------------------------------------------------------
	// Alle gegevens moeten correct zijn. -------------------------------
	@ParameterizedTest
	@ValueSource(ints = { 0, 1, 2, 3 })
	void speler_GeldigeAantalKroontjes_asserEquals(int aantalKroontjes) {
		Tegel tegel = new Tegel("Bos", aantalKroontjes);
		Assertions.assertEquals("Bos", tegel.getLandschap());
		Assertions.assertEquals(aantalKroontjes, tegel.getKroontjes());
	}

	// ------------------------------------------------------------------
	// Alle gegevens moeten fout zijn. ----------------------------------
	@ParameterizedTest
	@ValueSource(ints = { -100, -1, 4, 400 })
	void speler_OngeldigeLeeftijd_assertThrow(int aantalKroontjes) {
		assertThrows(IllegalArgumentException.class, () -> new Tegel("Bos", aantalKroontjes));
	}
}
