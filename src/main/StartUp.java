package main;

import domein.DomeinController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import taalmanager.vertaal;
import ui.MainApp;

public class StartUp extends Application {

	public static void main(String[] args) {

		vertaal.veranderTaal("nl");
		System.out.println(vertaal.geefWoord("CREATE_USER"));

		vertaal.veranderTaal("en");
		System.out.println(vertaal.geefWoord("CREATE_USER"));

		new MainApp(new DomeinController()).startConsoleGame();
		launch(args);

	}

	private DomeinController dc;

	@Override
	public void start(Stage primaryStage) {

		try {
			this.dc = new DomeinController();
			Parent root = FXMLLoader.load(getClass().getResource("/gui/menuStart.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
