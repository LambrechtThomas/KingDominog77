package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class IntermissionController {
	@FXML
	private ImageView imgLogo;
	@FXML
	private Label lblTitel;
	@FXML
	private Label lblInfo;
	@FXML
	private Button btnExit;
	@FXML
	private Button btnStartSpel;
	@FXML
	private Button btnVoegSpelerToe;
	@FXML
	private Button btnLogIn;

	// Event Listener on Button[#btnExit].onAction
	@FXML
	public void StopSpel(ActionEvent event) {
		toonInfo("Er is geen StopSpel actie toegevoegd.");

	}

	// Event Listener on Button[#btnStartSpel].onAction
	@FXML
	public void StartSpel(ActionEvent event) {
		toonInfo("Er is geen StartSpel actie toegevoegd.");
	}

	// Event Listener on Button[#btnVoegSpelerToe].onAction
	@FXML
	public void VoegSpelerToe(ActionEvent event) {
		toonFoutmelding("Er is geen VoegSpelerToe actie toegevoegd.");
	}

	// Event Listener on Button[#btnLogIn].onAction
	@FXML
	public void LogIn(ActionEvent event) {
		toonFoutmelding("Er is geen LOGIN actie toegevoegd.");
	}

	private void toonFoutmelding(String melding) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setContentText(melding);
		alert.showAndWait();
	}

	private void toonInfo(String melding) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setContentText(melding);
		alert.showAndWait();
	}
}
