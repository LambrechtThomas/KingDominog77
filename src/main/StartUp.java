package main;

import domein.DomeinController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.MainApp;

// StartUp class om de GUI op te stellen via FXML
public class StartUp extends Application {

	public static void main(String[] args) {
<<<<<<< Updated upstream
		// new MainApp(new DomeinController()).start();

		launch(args);

		// launch(args);

		// new DomeinController().startConsoleGame();
=======
		//launch(args);
		new MainApp(new DomeinController()).startConsoleGame();;
>>>>>>> Stashed changes

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
