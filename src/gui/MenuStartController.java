package gui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import taalmanager.vertaal;

public class MenuStartController {

	private Stage stage;
	private Scene scene;
	private Parent root;

	// INFO: sts____ == switchToScene_____

	@FXML
	private Label lblOnderTitel;

	@FXML
	private Label lblTitel;

	@FXML
	private Button btnInstelling;

	@FXML
	private Button btnJoinSpel;

	@FXML
	private Button btnLogin;

	@FXML
	private Button btnNieuwSpel;

	@FXML

	public void initialize() {

		lblTitel.setText(vertaal.geefWoord("START_A_NEW_GAME"));

		lblTitel.setText(vertaal.geefWoord("LOGIjN"));
	}

	public void switchToSceneLogin(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("menuLogin.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	public void switchToSceneInstellingen(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("menuInstellingen.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	public void switchToSceneSettings(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("menuSettings.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	public void switchToSceneGameBord(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("gameBord.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

}
