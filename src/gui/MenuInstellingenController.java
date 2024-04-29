package gui;

import java.io.IOException;

import domein.DomeinController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import taalmanager.vertaal;

public class MenuInstellingenController {

	private Stage stage;
	private Scene scene;
	private Parent root;
	
	private DomeinController dc;
	
	public void setDc(DomeinController dc) {
		this.dc = dc;
	}
	
	@FXML
	private Button btnEnglish;

	@FXML
	private Button btnFrancais;

	@FXML
	private Button btnNederlands;

	@FXML
	private Button btnTerugNaarStart;

	@FXML
	private Label lbInstellingen;

	public void initialize() {

		updateLabels();

		// TAAL NAAR ENGELS
		btnEnglish.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				vertaal.veranderTaal("en");
				updateLabels();
			}
		});

		// TAAL NAAR NEDERLANDS
		btnNederlands.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				vertaal.veranderTaal("nl");
				updateLabels();
			}
		});

		// TAAL NAAR FRANS
		btnFrancais.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				vertaal.veranderTaal("fr");
				updateLabels();
			}
		});
	}

	private void updateLabels() {
		btnEnglish.setText(vertaal.geefWoord("ENGLISH"));
		btnNederlands.setText(vertaal.geefWoord("NEDERLANDS"));
		btnFrancais.setText(vertaal.geefWoord("FRANCAIS"));

		btnTerugNaarStart.setText(vertaal.geefWoord("BACK_TO_START"));

		lbInstellingen.setText(vertaal.geefWoord("SETTINGS"));

	}

	public void switchToSceneStart(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("menuStart.fxml"));
		Parent root = loader.load();
		
		MenuStartController controller = loader.getController();
		controller.setDc(dc);
		
		
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

}
