package main;

import gui.WelkomScherm;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StartUp extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	// Start methode "aanpassen"
	@Override
	public void start(Stage primaryStage) {

		try {
			// Scherm aanmaken
			WelkomScherm root = new WelkomScherm();

			// Scene aan het scherm toevoegen
			Scene scene = new Scene(root, 500, 300);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Beta Kingdominos");
			primaryStage.show();
//			primaryStage.setMaximized(true);
//			primaryStage.setFullScreen(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
