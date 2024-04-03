package gui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneController {

	private Stage stage;
	private Scene scene;
	private Parent root;

	// INFO: sts____ == switchToScene_____

	public void stsStart(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("menuStart.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	public void stsLogin(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("menuLogin.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	public void stsInstellingen(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("menuInstellingen.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	public void stsSettings(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("menuSettings.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

}
