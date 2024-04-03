package main;

import domein.DomeinController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StartUp extends Application {

	public static void main(String[] args) {
		// new MainApp(new DomeinController()).start();
		launch(args);

	}

	private DomeinController dc;

	@Override
	public void start(Stage primaryStage) {

		try {
			this.dc = new DomeinController();
			Parent root = FXMLLoader.load(getClass().getResource("menuStart.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
