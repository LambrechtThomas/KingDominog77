package main;

import domein.DomeinController;
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

		Scene scene = new Scene(new MenuStartController(dc, primaryStage));

		primaryStage.setScene(scene);

		primaryStage.show();

	}

	public static void main(String[] args) {
//		new MainApp(new DomeinController()).startConsoleGame();
		launch(args);

	}

}
