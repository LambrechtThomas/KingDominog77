package ui;

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
		do {
			System.out.print("Naam speler: ");
			naam = invoer.nextLine();
		} while (naam == null || naam.isBlank()); // TODO error toevoegen
		return naam;
	}

	private int vraagNaarGeboortjaar() {
		int jaar;
		do {
			System.out.print("Geboortejaar: ");
			jaar = invoer.nextInt();
		} while (jaar < 1900 || jaar > 2024); // TODO error toevoegen

		return jaar;
	}

}
