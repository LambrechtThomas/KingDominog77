package ui;

import java.time.Year;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import DTO.dominoTegelDTO;
import DTO.spelerDTO;
import comparator.dominoTegelComparator;
import domein.DomeinController;
import domein.Kleur;
import taalmanager.vertaal;

public class MainApp {
	private DomeinController dc;

	private ArrayList<spelerDTO> beschikbareSpelers;
	private ArrayList<Kleur> beschikbareKleueren;

	private Scanner input;

	public MainApp(DomeinController dc) {
		this.dc = dc;

		beschikbareSpelers = dc.geefBeschikbareSpelers();
		beschikbareKleueren = dc.geefBeschikbareKleuren();

		input = new Scanner(System.in);
		vertaal.veranderTaal("en");
	}

	public void startConsoleGame() {

		System.out.print("\n\n== " + vertaal.geefWoord("WELCOME_BACK") + " == \n");
		int keuze = keuzeMenu();

		while (keuze != 4) {
			if (keuze == 1) {
				startRegistratie();
			}

			if (keuze == 2) {
				startSpel();
			}

			if (keuze == 3) {
				startTaalVerandering();
			}

			keuze = keuzeMenu();
		}

		System.out.print("\n\n== " + vertaal.geefWoord("STOP") + " == \n");
		System.exit(0);
	}

	public int keuzeMenu() {
		int keuze;
		boolean fout = false;

		do {
			if (fout)
				System.out.printf("%s %n", vertaal.geefWoord("VALID_NUM"));

			System.out.printf("%n== %s ==%n", vertaal.geefWoord("MENU"));
			System.out.printf("1: %s %n2: %s %n3: %s %n4: %s %n%n", vertaal.geefWoord("REGISTER_PLAYER"),
					vertaal.geefWoord("PLAY_GAME"), vertaal.geefWoord("LANGUAGE"), vertaal.geefWoord("STOP"));
			System.out.printf("%s ", vertaal.geefWoord("CHOICE"));

			keuze = input.nextInt();

			fout = true;
		} while (keuze < 1 || keuze > 4);

		return keuze;
	}

	private void startSpel() {

		System.out.printf("%n== %s == %n", vertaal.geefWoord("PLAY_GAME"));

		System.out.printf("%s%n", vertaal.geefWoord("HOW_MUCH_PLAYER"));
		dc.clearDeelnemedeSpeler();
		int hoeveelSpelers = vraagEenGetal(3, 4);

		for (int i = 0; i < hoeveelSpelers; i++) {
			System.out.printf("%n%s %d: %n", vertaal.geefWoord("PLAYER"), i + 1);
			
			try {
				dc.spelerDoetMee(kiesEenSpeler(), kiesEenKleur()); // hier
				
			} catch (Exception e) {
				System.err.println(e);
				startConsoleGame();
			}
		}

		try {
			dc.startSpel(); // hier
			
		} catch (Exception e) {
			System.err.println(e);
			startConsoleGame();
		}

		try {
			System.out.println(dc.geefDeelnemendeSpelers()); // hier
			
		} catch (Exception e) {
			System.err.println(e);
			startConsoleGame();
		}

		try {
			while (!dc.isSpelTenEinde()) { // hier
				System.out.printf("%n%s %d%n", vertaal.geefWoord("ROUND"), dc.getRonde()); // hier

				speelRonde();
			}
			
		} catch (Exception e) {
			System.err.println(e);
			startConsoleGame();
		}
	}

	private void speelRonde() {
		try {
			ArrayList<dominoTegelDTO> startKolom = dc.geefStartKolom();
			HashMap<spelerDTO, dominoTegelDTO> keuzes = new HashMap<>();
			int aantalSpelers = dc.geefAantalSpelers();	//Hier

			for (int i = 0; i < aantalSpelers; i++) {
				spelerDTO koning = dc.geefKoning();	//Hier

				System.out.printf("%s %s: %n", vertaal.geefWoord("IS_PLAYING"), koning.gebruikersnaam());

				int keuze = printDominos(startKolom);

				keuzes.put(koning, startKolom.stream().filter(v -> v.volgnummer() == keuze).findFirst().get());
				startKolom.remove(startKolom.stream().filter(v -> v.volgnummer() == keuze).findFirst().get());
				dc.kiesNieuweKoning(); //Hier
			}

			bevestiging(keuzes);
			
		} catch (Exception e) {
			System.err.println(e);
			startConsoleGame();
		}
	}

	private void bevestiging(HashMap<spelerDTO, dominoTegelDTO> keuzes) {
		System.out.printf("%n%s: %n", vertaal.geefWoord("CONFIRMATION"));

		for (Map.Entry<spelerDTO, dominoTegelDTO> entry : keuzes.entrySet()) {
			System.out.printf("%s %s %s %s%n", vertaal.geefWoord("FOR"), entry.getKey().gebruikersnaam(),
					vertaal.geefWoord("IS_IT"), entry.getValue().volgnummer());

		}

	}

	private int printDominos(ArrayList<dominoTegelDTO> startKolom) {
		ArrayList<Integer> mogelijkeKeuzes = new ArrayList<>();
		Collections.sort(startKolom, new dominoTegelComparator());

		for (dominoTegelDTO domino : startKolom) {
			mogelijkeKeuzes.add(domino.volgnummer());
			System.out.printf("%-2d: ╔═══════╦═══════╗ %n     %-7d %-7d  %n     %-7s %-7s  %n    ╚═══════╩═══════╝ %n",
					domino.volgnummer(), domino.tegels()[0].kroontjes(), domino.tegels()[1].kroontjes(),
					domino.tegels()[0].landschap(), domino.tegels()[1].landschap());
		}

		int getal;
		boolean fout = false;

		do {
			if (fout)
				System.out.printf("%s%n%n", vertaal.geefWoord("VALID_NUM"));

			System.out.printf("%s: ", vertaal.geefWoord("MAKE_YOUR_CHOICE"));

			getal = input.nextInt();

			fout = true;
		} while (!mogelijkeKeuzes.contains(getal));

		return getal;

	}

	private spelerDTO kiesEenSpeler() {
		System.out.printf("%s %n", vertaal.geefWoord("AVAILABLE_PLAYERS"));

		System.out.printf("0 : %s %n", vertaal.geefWoord("REGISTER_PLAYER"));

		int teller = 1;
		for (spelerDTO speler : beschikbareSpelers) {
			System.out.printf("%-2d: %s: %-18s %s: %d %n", teller++, vertaal.geefWoord("NAME"), speler.gebruikersnaam(),
					vertaal.geefWoord("BIRTHDAY"), speler.geboortejaar());

		}

		int gekozenSpelerIndex = vraagEenGetal(0, beschikbareSpelers.size());

		if (gekozenSpelerIndex == 0) {
			startRegistratie();
			return kiesEenSpeler();
		}

		spelerDTO gekozenSpeler = beschikbareSpelers.get(--gekozenSpelerIndex);
		beschikbareSpelers.remove(gekozenSpelerIndex);

		return gekozenSpeler;
	}

	private Kleur kiesEenKleur() {
		System.out.printf("%n%s%n", vertaal.geefWoord("AVAILABLE_COLOURS"));

		int teller = 1;
		for (Kleur kleur : beschikbareKleueren) {
			System.out.printf("%d: %s %n", teller++, kleur);
		}

		int gekozenKleur = vraagEenGetal(1, beschikbareKleueren.size());
		Kleur kleur = beschikbareKleueren.get(--gekozenKleur);
		beschikbareKleueren.remove(gekozenKleur);

		return kleur;
	}

	private void startRegistratie() {
		try {
			System.out.printf("%n== %s == %n", vertaal.geefWoord("REGISTER_PLAYER"));

			String gebruikersnaam = vraagGebruikersnaam();
			int geboortedatum = vraagGebrooteDatum();

			dc.registreerSpeler(gebruikersnaam, geboortedatum); // Hier

			System.out.printf("%n%s%n", vertaal.geefWoord("CONFIRMED"));

			beschikbareSpelers = dc.geefBeschikbareSpelers();
		} catch (Exception e) {
			System.err.println(e);
			startConsoleGame();
		}
	}

	private String vraagGebruikersnaam() {
		String gebruikersnaam = "";
		boolean correct = false;
		input.nextLine();

		do {
			try {
				System.out.printf("%s ", vertaal.geefWoord("ENTER_USER_ID"));
				gebruikersnaam = input.nextLine();

				correct = gebruikersnaam == null || gebruikersnaam.isBlank() || dc.bestaatSpeler(gebruikersnaam);
				if (!correct)
					System.out.printf("%s%n%n", vertaal.geefWoord("VALID_USER_ID"));
				
			} catch (Exception e) {
				System.err.print(e);
				input.nextLine();
			}
			
		} while (!correct);

		return gebruikersnaam;
	}

	private int vraagGebrooteDatum() {
		int gebrootedatum = 0;
		boolean correct = false;

		do {
			try {
				System.out.printf("%s ", vertaal.geefWoord("ENTER_BIRTHDAY"));
				gebrootedatum = input.nextInt();

				correct = gebrootedatum < Year.now().getValue() - 121 || gebrootedatum > Year.now().getValue();
				if (!correct)
					System.out.printf("%s%n%n", vertaal.geefWoord("VALID_DATE"));
				
			} catch (Exception e) {
				System.err.print(e);
				input.nextLine();
			}
			
		} while (!correct);

		return gebrootedatum;
	}

	private void startTaalVerandering() {
		System.out.printf("%s %n1: %s %n2: %s %n3: %s%n", vertaal.geefWoord("TALEN"), "English", "Nederlands",
				"Français");
		int gekozenGetal = vraagEenGetal(1, 3);
		switch (gekozenGetal) {
		case 1:
			vertaal.veranderTaal("en");
			break;
		case 2:
			vertaal.veranderTaal("nl");
			break;
		case 3:
			vertaal.veranderTaal("fr");
			break;
		}
	}

	public int vraagEenGetal(int ondergrens, int bovengrens) {
		int getal = 0;
		boolean correct = false;

		do {
			try {
				System.out.printf("%s ", vertaal.geefWoord("MAKE_UR_CHOICE"));
				getal = input.nextInt();

				correct = getal < ondergrens || getal > bovengrens;
				if (!correct)
					System.out.printf("%s%n%n", vertaal.geefWoord("VALID_NUM"));
				
			} catch (Exception e) {
				System.err.print(e);
				input.nextLine();
			}
		} while (!correct);

		return getal;
	}

}