package testen;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domein.DominoTegel;
import domein.KasteelTegel;
import domein.Koninkrijk;
import domein.Kleur;
import domein.Tegel;

class KoninkrijkTest {

	private Koninkrijk koninkrijk;
	
	private Tegel tegel = new Tegel("mijn", 2);
	private Tegel[] tegels = { tegel };

	@BeforeEach
	public void setUp() {
		koninkrijk = new Koninkrijk();
	}

	// ------------------------------------------------------------------
	// Tests voor setKasteel. ----------------------------------------

	@Test
	void koninkrijk_setKasteel_metNullTegel_gooitException() {
		assertThrows(IllegalArgumentException.class, () -> koninkrijk.setKasteel(0, 0, null));
	}

	@Test
	void koninkrijk_setKasteel_metGeldigeTegel_zetKasteel() {
		KasteelTegel kasteel = new KasteelTegel();
		koninkrijk.setKasteel(0, 0, kasteel);

		assertEquals(kasteel, koninkrijk.getBord()[0][0]);
	}

	// ------------------------------------------------------------------
	// Tests voor plaatsDomino. ---------------------------------------

	@Test
	void koninkrijk_plaatsDomino_metNullTegel_gooitException() {
		assertThrows(IllegalArgumentException.class, () -> koninkrijk.plaatsDomino(null, 0, 0));
	}

	@Test
	void koninkrijk_plaatsDomino_metTegelOpBezetVak_gooitException() {
		koninkrijk.setKasteel(0, 0, new KasteelTegel());

		assertThrows(IllegalArgumentException.class, () -> koninkrijk.plaatsDomino(new DominoTegel(1, tegels), 0, 0));
	}

	@Test
	void koninkrijk_plaatsDomino_metGeldigeTegel_plaatstDomino() {
		DominoTegel domino = new DominoTegel(2, tegels);
		koninkrijk.plaatsDomino(domino, 0, 1);

		assertEquals(domino, koninkrijk.getBord()[0][1]);
	}

	// ------------------------------------------------------------------
	// Tests voor isZelfdeTegel. ---------------------------------------

	@Test
	void koninkrijk_isZelfdeTegel_metNullTegels_isFalse() {
		assertFalse(koninkrijk.isZelfdeTegel(null, null));
	}

	@Test
	void koninkrijk_isZelfdeTegel_metVerschillendeLandschappen_isFalse() {
		Tegel tegel1 = new Tegel("mijn", 0);
		Tegel tegel2 = new Tegel("mijn", 1);

		assertFalse(koninkrijk.isZelfdeTegel(tegel1, tegel2));
	}

	@Test
	void koninkrijk_isZelfdeTegel_metGelijkeLandschappen_isTrue() {
		Tegel tegel1 = new Tegel("mijn", 0);
		Tegel tegel2 = new Tegel("mijn", 1);

		assertTrue(koninkrijk.isZelfdeTegel(tegel1, tegel2));
	}

	// ------------------------------------------------------------------
	// Tests voor isBezet. -------------------------------------------

	@Test
	void koninkrijk_isBezet_metBezetVak_isTrue() {
		koninkrijk.setKasteel(0, 0, new KasteelTegel());

		assertTrue(koninkrijk.isBezet(0, 0));
	}

	@Test
	void koninkrijk_isBezet_metLeegVak_isFalse() {
		assertFalse(koninkrijk.isBezet(0, 0));
	}

	/// ------------------------------------------------------------------
	// Tests voor isBordVolzet. --------------------------------------

	@Test
	void koninkrijk_isBordVolzet_metVolledigBord_isTrue() {
		for (int i = 0; i < koninkrijk.getBord().length; i++) {
			for (int j = 0; j < koninkrijk.getBord().length; j++) {
				koninkrijk.getBord()[i][j] = new Tegel("mijn", 1);
			}
		}

		assertTrue(koninkrijk.isBordVolzet());
	}
}