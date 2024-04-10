package domein;

import java.time.Year;
import java.util.ArrayList;
import java.util.Scanner;

import DTO.spelerDTO;

public class DomeinController {

	private final SpelerRepository spelerRepository;
	private Spel huidigSpel;

	private ArrayList<Speler> deelnemendeSpelers;
	private ArrayList<Speler> beschikbareSpelers;

	private Scanner input;

	public DomeinController() {
		spelerRepository = new SpelerRepository();
		input = new Scanner(System.in);
	}

	public void registreerSpeler(String gebruikersnaam, int geboortejaar) {
		Speler nieuweSpeler = new Speler(gebruikersnaam, geboortejaar);
		spelerRepository.voegToe(nieuweSpeler);
	}

	public void spelerDoetMee(spelerDTO speler, Kleur kleur) {
		// Checken of alles ok is?
		
	}

	public void startSpel() {
		// Nakijken of deelnemendeSpelers 3 of 4 zijn en of ze kleuren hebben

		huidigSpel = new Spel(deelnemendeSpelers);
	}

//	public ArrayList<SpelerDTO> geefBeschikbareSpelers() {
//
//	}

	public ArrayList<Kleur> geefBeschikbareKleuren() {
		return huidigSpel.geefBeschikbareKleuren();
	}

	// public SpelerDTO geefSpelerAanbeurt() {}

	public void startConsoleGame() {
		System.out.print("Welkom bij KINGDOMINO !! \n");
		int keuze = keuzeMenu();

		while (keuze != 3) {
			if (keuze == 1) {
				System.out.println("\n==Registeer een Speler==");
				String gebruikersnaam = vraagGebruikersnaam();
				int gebrootedatum = vraagGebrooteDatum();
				spelerRepository.voegToe(new Speler(gebruikersnaam, gebrootedatum, 0, 0));

				keuze = keuzeMenu();
			}

			if (keuze == 2) {
				System.out.println("\n==Speel Spel==");
				System.out.printf("Er zijn volgende spelers beschikbaar: %s",
						spelerRepository.geefLijstBestaandeSpelers());

				keuze = keuzeMenu();
			}
		}

		System.out.printf("%n%n==Stopping==");

	}

	public int keuzeMenu() {
		int keuze;
		boolean fout = false;

		do {
			if (fout)
				System.out.println("\nGelieve een geldige nummer in tegeven");

			System.out.print("\n==Menu==\n");
			System.out.printf("1: Registeer Speler %n2: Speel spel %n3: Stop %n%n");
			System.out.printf("welke keuze?? ");
			keuze = input.nextInt();

			fout = true;
		} while (keuze < 1 || keuze > 3);

		return keuze;
	}

	private String vraagGebruikersnaam() {
		String gebruikersnaam;
		boolean fout = false;
		input.nextLine();
		boolean correct = false;

		do {
			if (fout)
				System.out.println("\nGelieve een geldige naam in te geven");

			System.out.printf("%nGeef je gebruikersnaam in: ");
			gebruikersnaam = input.nextLine();

			fout = true;
		} while (gebruikersnaam == null || gebruikersnaam.isBlank() || spelerRepository.bestaatSpeler(gebruikersnaam));

		return gebruikersnaam;
	}

	private int vraagGebrooteDatum() {
		int gebrootedatum;
		boolean fout = false;

		do {
			if (fout)
				System.out.println("\nGelieve een geldige datum in te geven");

			System.out.printf("%nGeef je geboortedatum: ");
			gebrootedatum = input.nextInt();

			fout = true;
		} while (gebrootedatum < Year.now().getValue() - 121 || gebrootedatum > Year.now().getValue());

		return gebrootedatum;
	}

}
