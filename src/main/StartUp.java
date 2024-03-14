package main;

import domein.DomeinController;
import ui.MainApp;

public class StartUp {

	public static void main(String[] args) {
		new MainApp(new DomeinController()).startRegistratie();
	}

}
