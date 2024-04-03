package main;

import domein.DomeinController;
import gui.WelkomScherm;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.MainApp;

public class StartUp extends Application {

	public static void main(String[] args) {
		new MainApp(new DomeinController()).start();
		launch(args);

	}

	private DomeinController dc;

	@Override
	public void start(Stage primaryStage) {

		try {
			this.dc = new DomeinController();
			WelkomScherm root = new WelkomScherm(dc);

			// Scene aan het scherm toevoegen
			Scene scene = new Scene(root, 750, 250);
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
