package main;

import domein.DomeinController;
import gui.MenuStartController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import taalmanager.vertaal;

public class StartUp extends Application {
	private DomeinController dc;

	public static void main(String[] args) {
//		new MainApp(new DomeinController()).startConsoleGame();
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		vertaal.veranderTaal("en");
		Image image = new Image("file:assets/favicon.png");
		primaryStage.getIcons().add(image);
		primaryStage.setTitle("Kingdomino - Groep 77");

		try {
			this.dc = new DomeinController();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/menuStart.fxml"));
			Parent root = loader.load();

			MenuStartController controller = loader.getController();
			controller.setDc(dc);

			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
