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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MenuLoginController {

	private Stage stage;
	private Scene scene;
	private Parent root;

	@FXML
	private Button btnLogin;

	@FXML
	private Button btnNaarStart;

	@FXML
	private TextField fldGeboortedatum;

	@FXML
	private TextField fldGebruikersnaam;

	@FXML
	private Label lblGeboortedatum;

	@FXML
	private Label lblGebruikersnaam;

	@FXML
	private Label lblLogInOfMaakAccountAan;

	@FXML
	private Label lblWelkcomTerug;

	public void switchToSceneStart(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("menuStart.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}
