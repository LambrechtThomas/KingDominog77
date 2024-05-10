package gui;

import java.io.IOException;

import domein.DomeinController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import taalmanager.vertaal;

public class MenuInstellingenController extends AnchorPane {

	private Stage stage;
	private Scene scene;
	private Parent root;

	private DomeinController dc;

	// ======================================================

	private void loadFxmlScreen(String name) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(name));
		loader.setRoot(this);
		loader.setController(this);
		try {
			loader.load();
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}

	public MenuInstellingenController(DomeinController dc2, Stage stage) {
		loadFxmlScreen("menuInstellingen.fxml");
		this.dc = dc2;
		this.stage = stage;
	}

	// ======================================================

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

		btnTerugNaarStart.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				MenuStartController scene = new MenuStartController(dc, stage);
				getScene().setRoot(scene);
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

}
