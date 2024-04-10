package testen;

import static org.junit.jupiter.api.Assertions.*;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import domein.DominoTegel;
import domein.DominoTegelRepository;
import domein.Kleur;
import domein.Koninkrijk;
import domein.Speler;
import domein.Tegel;
import domein.Spel;

class SpelTest {

	private Spel spel; // Het spel dat we testen
	private ArrayList<Speler> spelers; // De spelers in het spel
	private DominoTegelRepository dominoRepo; // De repository met alle dominotegels
	private SecureRandom sr; // Een random generator

	@BeforeEach
	public void setUp() {
		// Spelers initialiseren
		spelers = new ArrayList<>();
		spelers.add(new Speler("Tester1", 2000));
		spelers.add(new Speler("Tester2", 2000));
		spelers.add(new Speler("Tester3", 2000));

		// DominoTegelRepository initialiseren
		dominoRepo = new DominoTegelRepository();

		// SecureRandom initialiseren
		sr = new SecureRandom();

		// Spel initialiseren met de spelers
		spel = new Spel(spelers);
	}

	// ------------------------------------------------------------------
	// Alle tests voor de constructor. ---------------------------------

	@Test
	void spel_constructor_maaktSpel() {
		// Controleer of het spel correct is geconstrueerd
		Assertions.assertNotNull(spel); // Het spel mag niet null zijn
		Assertions.assertEquals(spelers, spel.getHuidigeSpelers()); // De spelers in het spel moeten overeenkomen met de spelers die we hebben meegegeven
		Assertions.assertNotNull(spel.getKoning()); // Er moet een koning zijn
		Assertions.assertNotNull(spel.getSpelerAanbeurt()); // Er moet een speler aan de beurt zijn
	}

	// ------------------------------------------------------------------
	// Tests voor setStartKolom. ----------------------------------------

	@ParameterizedTest
	@NullAndEmptySource
	void spel_setStartKolom_metNullOflegeLijst_gooitException(ArrayList<DominoTegel> startKolom) {
		// Zet de startkolom met een lege of null lijst
		assertThrows(IllegalArgumentException.class, () -> spel.setStartKolom(startKolom));
	}

	@Test
	void spel_setStartKolom_metGeldigeLijst_zetStartKolom() {
		// Nieuwe startkolom initialiseren
		ArrayList<DominoTegel> startKolom = new ArrayList<>();
		startKolom.add(new DominoTegel(1, new Tegel[1])); // ==> Nog niet 100% correct ___ MAURO plz kijk
		startKolom.add(new DominoTegel(2, new Tegel[1])); // ==> Nog niet 100% correct ___ MAURO plz kijk

		// De startkolom van het spel instellen
		spel.setStartKolom(startKolom);

		// Controleren of de startkolom correct is ingesteld
		Assertions.assertEquals(startKolom, spel.getStartKolom()); // De startkolom van het spel moet overeenkomen met de startkolom die we hebben meegegeven
	}

	// ------------------------------------------------------------------
	// Tests voor setEindKolom. ----------------------------------------

	@ParameterizedTest
	@NullAndEmptySource
	void spel_setEindKolom_metNullOflegeLijst_gooitException(ArrayList<DominoTegel> eindKolom) {
		// Zet de eindkolom met een lege of null lijst
		assertThrows(IllegalArgumentException.class, () -> spel.setEindKolom(eindKolom));
	}

	@Test
	void spel_setEindKolom_metGeldigeLijst_zetEindKolom() {
		// Nieuwe eindkolom initialiseren
		ArrayList<DominoTegel> eindKolom = new ArrayList<>();
		eindKolom.add(new DominoTegel(3, new Tegel[1])); // ==> ___ MAURO plz kijk
		eindKolom.add(new DominoTegel(4, new Tegel[1])); // ==> ___ MAURO plz kijk
	}
}
