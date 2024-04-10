package testen;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import domein.Tegel;

class TegelTest {

	// ------------------------------------------------------------------
	// Tests voor de constructor met landschap en kroontjes. ------------

	@ParameterizedTest
	@ValueSource(ints = { -1, 4 })
	void tegel_constructor_metOngeldigAantalKroontjes_gooitException(int kroontjes) {
		assertThrows(IllegalArgumentException.class, () -> new Tegel("Gras", kroontjes));
	}

	@Test
	void tegel_constructor_metNullLandschap_gooitException() {
		assertThrows(IllegalArgumentException.class, () -> new Tegel(null, 2));
	}

	@Test
	void tegel_constructor_metGeldigeLandschapEnKroontjes_maaktTegel() {
		Tegel tegel = new Tegel("Gras", 2);

		assertEquals("Gras", tegel.getLandschap());
		assertEquals(2, tegel.getKroontjes());
	}

	// ------------------------------------------------------------------
	// Tests voor de constructor zonder parameters. --------------------

	@Test
	void tegel_constructor_zonderParameters_maaktKasteelTegel() {
		Tegel tegel = new Tegel();

		assertEquals("Kasteel", tegel.getLandschap());
		assertEquals(0, tegel.getKroontjes());
	}

	// ------------------------------------------------------------------
	// Tests voor de getters. -----------------------------------------

	@Test
	void tegel_getLandschap_geeftLandschap() {
		Tegel tegel = new Tegel("Gras", 2);

		assertEquals("Gras", tegel.getLandschap());
	}

	@Test
	void tegel_getKroontjes_geeftKroontjes() {
		Tegel tegel = new Tegel("Gras", 2);

		assertEquals(2, tegel.getKroontjes());
	}
}
