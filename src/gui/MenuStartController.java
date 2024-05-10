package gui;

import java.io.IOException;

import domein.DomeinController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import taalmanager.vertaal;

public class MenuStartController extends AnchorPane {

	private Stage stage;
	private Scene scene;
	private Parent root;

	private DomeinController dc;

	// -------------------------------------------------------------------

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

	public MenuStartController(DomeinController dc2, Stage stage) {
		loadFxmlScreen("menuStart.fxml");
		this.dc = dc2;
		this.stage = stage;
	}

	// -------------------------------------------------------------------

	@FXML
	private Label lblOnderTitel;

	@FXML
	private Button btnInstelling;

	@FXML
	private Button btnLogin;

	@FXML
	private Button btnNieuwSpel;

	@FXML

	public void initialize() {

		btnNieuwSpel.setText(vertaal.geefWoord("START_A_NEW_GAME"));

		btnLogin.setText(vertaal.geefWoord("LOGIN"));

		btnInstelling.setText(vertaal.geefWoord("SETTINGS"));
	}

	public void switchToSceneLogin(ActionEvent event) throws IOException {
		Scene sc = new Scene(new MenuLoginController(dc, stage));
		stage.setScene(sc);
	}

	public void switchToSceneInstellingen(ActionEvent event) throws IOException {
		Scene sc = new Scene(new MenuInstellingenController(dc, stage));
		stage.setScene(sc);

	}

	public void switchToSceneGameBord(ActionEvent event) throws IOException {
		Scene sc = new Scene(new gameBordController(dc, stage));
		stage.setScene(sc);
	}

}
