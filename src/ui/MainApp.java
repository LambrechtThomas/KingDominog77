package ui;

import java.util.InputMismatchException;
import java.util.Scanner;

import domein.DomeinController;

public class MainApp {

	private Scanner invoer = new Scanner(System.in);
	DomeinController dc;

	public MainApp(DomeinController dc) {
		this.dc = dc;
	}

	public void startRegistratie() {

		String naam = vraagNaarNaam();
		int jaar = vraagNaarGeboortjaar();

		dc.registreerSpeler(naam, jaar);

	}

	// Methodes

	private String vraagNaarNaam() {
		String naam;
		boolean correct = false;
		do {// probeerd blok of invulling naam voldoet aan parrameters in if blok zo niet
			// throw passende melding
			try {
				System.out.print("Naam speler: ");
				naam = invoer.nextLine();
				if (naam == null || naam.isBlank()) {
					throw new IllegalArgumentException("naam kan enkel letters bevatten.");
				}
			} catch (InputMismatchException e) {
				System.out.println("geen geldige String ingevuld. Porbeer opnieuw.");
				return vraagNaarNaam();
			} catch (IllegalArgumentException e) {
				System.err.println(e.getMessage());
				return vraagNaarNaam();
			}
			correct = true;
		} while (!correct);
		return naam;
	}

	private int vraagNaarGeboortjaar() {
		int jaar = 0;
		boolean correct = false;
		do {
			try {
				// probeerd blok of invulling jaar voldoet aan parrameters in if blok zo niet
				// throw passende melding
				System.out.print("Geboortejaar: ");
				jaar = invoer.nextInt();
				if (jaar < 1900 || jaar > 2024) {
					throw new IllegalArgumentException("Geboortejaar moet tussen 1900 en 2024 liggen.");
				}
			} catch (InputMismatchException e) {

				System.err.println("Geen geldig getal ingevoerd. Probeer opnieuw.");

			} catch (IllegalArgumentException e) {
				System.err.println(e.getMessage());

			}
			correct = true;
		} while (!correct);
		return jaar;

	}

}
