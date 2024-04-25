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
	}

	public void startConsoleGame() {

		System.out.print("\n\n== " + vertaal.geefWoord("WELCOME_BACK") + " == \n");
		int keuze = keuzeMenu();

		while (keuze != 3) {
			if (keuze == 1) {
				startRegistratie();
			}

			if (keuze == 2) {
				startSpel();
			}

			keuze = keuzeMenu();
		}

		System.out.print("\n\n== " + vertaal.geefWoord("STOP") + " == \n");
	}

	public int keuzeMenu() {
		int keuze;
		boolean fout = false;

		do {
			if (fout)
				// System.out.println("\nGelieve een geldige nummer in tegeven");
				System.out.print("\n" + vertaal.geefWoord("VALID_NUM "));

			// System.out.print("\n==Menu==\n");
			System.out.print("\n== " + vertaal.geefWoord("MENU") + " == \n");

			// System.out.printf("1: Registeer Speler %n2: Speel spel %n3: Stop %n%n");
			System.out.print("1: " + vertaal.geefWoord("REGISTER_PLAYER") + "%n2: " + vertaal.geefWoord("PLAY_GAME")
					+ "%n3: " + vertaal.geefWoord("STOP") + " == \n");

			// System.out.printf("welke keuze?? ");
			System.out.print(vertaal.geefWoord("CHOICE"));

			keuze = input.nextInt();

			fout = true;
		} while (keuze < 1 || keuze > 3);

		return keuze;
	}

	private void startSpel() {
		// System.out.println("\n==Speel Spel==");
		System.out.print("\n== " + vertaal.geefWoord("PLAY_GAME") + " ==");

		// System.out.print("Met hoeveel wenst u te spelen? ");
		System.out.print(vertaal.geefWoord("HOW_MUCH_PLAYER"));

		int hoeveelSpelers = vraagEenGetal(3, 4);

		for (int i = 0; i < hoeveelSpelers; i++) {
			// System.out.printf("Speler +%d", i + 1);
			System.out.printf(vertaal.geefWoord("PLAYER") + "%d", i + 1);

			dc.spelerDoetMee(kiesEenSpeler(), kiesEenKleur());
		}

		dc.startSpel();

		System.out.println(dc.geefDeelnemendeSpelers());

		while (!dc.isSpelTenEinde()) {
			// System.out.printf("%nHet is ronde %d%n", dc.getRonde());
			System.out.print("%n" + vertaal.geefWoord("ROUND") + "%d%n" + dc.getRonde());

			speelRonde();

		}

	}

	private void speelRonde() {
		ArrayList<dominoTegelDTO> startKolom = dc.geefStartKolom();
		HashMap<spelerDTO, dominoTegelDTO> keuzes = new HashMap<>();
		int aantalSpelers = dc.geefAantalSpelers();

		for (int i = 0; i < aantalSpelers; i++) {
			spelerDTO koning = dc.geefKoning();

			// System.out.printf("Het is aan %s: %n", koning.gebruikersnaam());
			System.out.printf(vertaal.geefWoord("IS_PLAYING") + "%s: %n", koning.gebruikersnaam());

			int keuze = printDominos(startKolom);

			keuzes.put(koning, startKolom.stream().filter(v -> v.volgnummer() == keuze).findFirst().get());
			startKolom.remove(startKolom.stream().filter(v -> v.volgnummer() == keuze).findFirst().get());
			dc.kiesNieuweKoning();
		}

		bevestiging(keuzes);
	}

	private void bevestiging(HashMap<spelerDTO, dominoTegelDTO> keuzes) {
		// System.out.printf("%n%n%nDit is een bevestiging:%n");
		System.out.print("%n%n%n" + vertaal.geefWoord("CONFIRMATION") + ":%n");

		for (Map.Entry<spelerDTO, dominoTegelDTO> entry : keuzes.entrySet()) {
			// System.out.printf("Voor %s is het %s%n", entry.getKey().gebruikersnaam(),
			// entry.getValue().volgnummer());
			System.out.printf(vertaal.geefWoord("FOR") + "%s" + vertaal.geefWoord("IS_IT") + "%s%n"
					+ entry.getKey().gebruikersnaam(), entry.getValue().volgnummer());

		}

	}

	private int printDominos(ArrayList<dominoTegelDTO> startKolom) {
		ArrayList<Integer> mogelijkeKeuzes = new ArrayList<>();
		Collections.sort(startKolom, new dominoTegelComparator());

		for (dominoTegelDTO domino : startKolom) {
			mogelijkeKeuzes.add(domino.volgnummer());
			System.out.printf("%-2d: ╔═══════╦═══════╗ %n     %-7d %-7d  %n     %-7s %-7s  %n    ╚═══════╩═══════╝ %n",
					domino.volgnummer(), domino.tegels()[0].getKroontjes(), domino.tegels()[1].getKroontjes(),
					domino.tegels()[0].getLandschap(), domino.tegels()[1].getLandschap());
		}

		int getal;
		boolean fout = false;

		do {
			if (fout)
				// System.out.println("\nGelieve een geldige getal in te geven");
				System.out.print("\\n" + vertaal.geefWoord("VALID_NUM"));

			// System.out.printf("Maak uw keuze: ");
			System.out.print(vertaal.geefWoord("MAKE_YOUR_CHOICE"));

			getal = input.nextInt();

			fout = true;
		} while (!mogelijkeKeuzes.contains(getal));

		return getal;

	}

	private spelerDTO kiesEenSpeler() {
		// System.out.printf("\nDe volgende spelers zijn beschikbaar: \n");
		System.out.print("\n" + vertaal.geefWoord("AVAILABLE_PLAYERS") + "\n");

		// System.out.println("0: Registreer nieuwe speler");
		System.out.print("0:" + vertaal.geefWoord("REGISTER_PLAYER"));

		int teller = 1;
		for (spelerDTO speler : beschikbareSpelers) {
			// System.out.printf("%d: Naam: %-18s Geboortedatum: %d %n", teller++,
			// speler.gebruikersnaam(),
			// speler.geboortejaar());
			System.out.printf("%d:" + vertaal.geefWoord("NAME") + ": %-18s" + vertaal.geefWoord("BIRTHDAY") + ": %d %n",
					teller++, speler.gebruikersnaam(), speler.geboortejaar());

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

		// System.out.printf("%nDe volgende kleuren zijn beschikbaar: %n");
		System.out.print("%n" + vertaal.geefWoord("AVAILABLE_COLOURS") + "%n");

		int teller = 1;
		for (Kleur kleur : beschikbareKleueren) {
			System.out.printf("%d: %s %n", teller++, kleur);
			// vertalen???

		}

		int gekozenKleur = vraagEenGetal(1, beschikbareKleueren.size());
		Kleur kleur = beschikbareKleueren.get(--gekozenKleur);
		beschikbareKleueren.remove(gekozenKleur);

		return kleur;
	}

	private void startRegistratie() {
		// System.out.println("\n==Registeer een Speler==");
		System.out.print("\n== " + vertaal.geefWoord("REGISTER_PLAYER") + " ==");

		String gebruikersnaam = vraagGebruikersnaam();
		int geboortedatum = vraagGebrooteDatum();

		dc.registreerSpeler(gebruikersnaam, geboortedatum);

		// System.out.println("\n\nGelukt");
		System.out.print("\n\n" + vertaal.geefWoord("CONFIRMED"));

		beschikbareSpelers = dc.geefBeschikbareSpelers();
	}

	private String vraagGebruikersnaam() {
		String gebruikersnaam;
		boolean fout = false;
		input.nextLine();
		boolean correct = false;

		do {
			if (fout)
				// System.out.println("\nGelieve een geldige naam in te geven");
				System.out.print("\n" + vertaal.geefWoord("VALID_USER_ID"));

			// System.out.printf("%nGeef je gebruikersnaam in: ");
			System.out.print("%n" + vertaal.geefWoord("ENTER_USER_ID"));

			gebruikersnaam = input.nextLine();

			fout = true;
		} while (gebruikersnaam == null || gebruikersnaam.isBlank() || dc.bestaatSpeler(gebruikersnaam));

		return gebruikersnaam;
	}

	private int vraagGebrooteDatum() {
		int gebrootedatum;
		boolean fout = false;

		do {
			if (fout)
				// System.out.println("\nGelieve een geldige datum in te geven");
				System.out.print("\n" + vertaal.geefWoord("VALID_DATE"));

			// System.out.printf("%nGeef je geboortedatum: ");
			System.out.print("%n" + vertaal.geefWoord("ENTER_BIRTHDAY"));

			gebrootedatum = input.nextInt();

			fout = true;
		} while (gebrootedatum < Year.now().getValue() - 121 || gebrootedatum > Year.now().getValue());

		return gebrootedatum;
	}

	public int vraagEenGetal(int ondergrens, int bovengrens) {
		int getal;
		boolean fout = false;

		do {
			if (fout)
				// System.out.println("\nGelieve een geldige getal in te geven");
				System.out.print("\n" + vertaal.geefWoord("VALID_NUM"));

			// System.out.printf("Maak uw keuze: ");
			System.out.print(vertaal.geefWoord("MAKE_UR_CHOICE"));

			getal = input.nextInt();

			fout = true;
		} while (getal < ondergrens || getal > bovengrens);

		return getal;
	}

}