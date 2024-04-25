package gui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import taalmanager.vertaal;

public class MenuInstellingenController {

	private Stage stage;
	private Scene scene;
	private Parent root;

	public void initialize() {

		btnEnglish.setText(vertaal.geefWoord("ENGLISH"));

		btnNederlands.setText(vertaal.geefWoord("NEDERLANDS"));

		btnFRANCAIS.setText(vertaal.geefWoord("FRANCAIS"));
	}

	public void switchToSceneStart(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("menuStart.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}
