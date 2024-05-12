package main;

import DTO.spelerDTO;
import domein.DomeinController;
import domein.Kleur;
import gui.MenuStartController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import taalmanager.vertaal;

public class StartUp extends Application {

	@Override
	public void start(Stage primaryStage) {

		vertaal.veranderTaal("en");
		primaryStage.getIcons().add(new Image("file:assets/favicon.png"));
		primaryStage.setTitle("Kingdomino - Groep 77");

		DomeinController dc = new DomeinController();
		
		try {
			dc.spelerDoetMee(new spelerDTO("Thomas Lambrecht", 2003, 0, 0, 0, null), Kleur.GEEL);
			dc.spelerDoetMee(new spelerDTO("Mauro Maratta", 2004, 0, 0, 0, null), Kleur.GROEN);
			dc.spelerDoetMee(new spelerDTO("Liesbeth Lewyllie", 2004, 0, 0, 0, null), Kleur.BLAUW);
			//dc.spelerDoetMee(new spelerDTO("Kobe Sapijn", 2004, 0, 0, 0, null), Kleur.ROZE);
		} catch(Exception e) {
			
		}

		Scene scene = new Scene(new MenuStartController(dc, primaryStage));

		primaryStage.setScene(scene);

		primaryStage.show();

	}

	public static void main(String[] args) {
//		new MainApp(new DomeinController()).startConsoleGame();
		launch(args);

	}

}
