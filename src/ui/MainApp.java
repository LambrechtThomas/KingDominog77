package ui;

import java.util.Scanner;

import domein.DomeinController;

public class MainApp {

	DomeinController dc;

	public MainApp(DomeinController dc) {
		this.dc = dc;
	}

	public void startRegistratie() {

		Scanner invoer = new Scanner(System.in);

		System.out.print("Naam speler: ");
		String naam = invoer.nextLine(); // TODO controle toevoegen

		System.out.print("Geboortejaar: ");
		int jaar = invoer.nextInt(); // TODO controle toevoegen

		dc.registreerSpeler("Xander", 2002);

	}

}
