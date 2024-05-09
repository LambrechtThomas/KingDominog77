package testen;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domein.DominoTegel;
import domein.Tegel;

class DominoTegelTest {

	private DominoTegel dominoTegel;

	@Test
	void dominoTegel_constructor_maaktDominoTegel() {
		Tegel tegel1 = new Tegel("Gras", 2);
		Tegel tegel2 = new Tegel("Bos", 1);
		dominoTegel = new DominoTegel(1, new Tegel[] { tegel1, tegel2 });

		assertEquals(1, dominoTegel.getVolgnummer());
		assertEquals(tegel1, dominoTegel.getTegels()[0]);
		assertEquals(tegel2, dominoTegel.getTegels()[1]);
	}

	// ------------------------------------------------------------------
	// Tests voor de getters. -----------------------------------------

	@Test
	void dominoTegel_getVolgnummer_geeftVolgnummer() {
		Tegel tegel1 = new Tegel("Gras", 2);
		Tegel tegel2 = new Tegel("Bos", 1);
		dominoTegel = new DominoTegel(1, new Tegel[] { tegel1, tegel2 });

		assertEquals(1, dominoTegel.getVolgnummer());
	}

	// ------------------------------------------------------------------
	// Tests voor draai(). ---------------------------------------------

	@Test
	void dominoTegel_draai_horizontaalWordtVerticaal() {
		dominoTegel = new DominoTegel(1, new Tegel[] { new Tegel("Gras", 2), new Tegel("Bos", 1) });
		dominoTegel.draai();

		assertFalse(dominoTegel.isHorizontaal());
	}

	@Test
	void dominoTegel_draai_verticaalWordtHorizontaal() {
		dominoTegel = new DominoTegel(1, new Tegel[] { new Tegel("Gras", 2), new Tegel("Bos", 1) });
		dominoTegel.draai();
		dominoTegel.draai();

		assertTrue(dominoTegel.isHorizontaal());
	}

//  @Test	
//	@DisplayName("Draaien verandert de orientatie")
//	  void draaien_verandertOrientatie(boolean isHorizontaal, boolean verwachteOrientatie) {
//	        DominoTegel dominoTegel = new DominoTegel(1, new Tegel[]{new Tegel("Gras", 2), new Tegel("Bos", 1)});
//	        dominoTegel.setHorizontaal(isHorizontaal);
//
//	        dominoTegel.draai();
//
//	        assertEquals(verwachteOrientatie, dominoTegel.isHorizontaal());
//	    }
//

	// ------------------------------------------------------------------
	// Tests voor spiegel(). -------------------------------------------

	@Test
	@DisplayName("Spiegelen verandert de spiegeling")
	void spiegelen_verandertSpiegeling() {
		DominoTegel dominoTegel = new DominoTegel(1, new Tegel[] { new Tegel("Gras", 2), new Tegel("Bos", 1) });
		dominoTegel.spiegel();

		assertEquals(true, dominoTegel.isSpiegeld());
	}
}

//	@Test
//	void dominoTegel_spiegel_nietGespiegeldWordtGespiegeld() {
//		dominoTegel = new DominoTegel(1, new Tegel[] { new Tegel("Gras", 2), new Tegel("Bos", 1) });
//		dominoTegel.spiegel();
//
//		assertTrue(dominoTegel.isSpiegeld());
//	}
//
//	@Test
//	void dominoTegel_spiegel_gespiegeldWordtNietGespiegeld() {
//		dominoTegel = new DominoTegel(1, new Tegel[] { new Tegel("Gras", 2), new Tegel("Bos", 1) });
//		dominoTegel.spiegel();
//		dominoTegel.spiegel();
//
//		assertFalse(dominoTegel.isSpiegeld());
//	}
//}
