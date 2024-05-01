package gui;

import java.io.IOException;
import java.lang.ModuleLayer.Controller;

import domein.DomeinController;
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

	private DomeinController dc;

	public void setDc(DomeinController dc) {
		this.dc = dc;
		
		try {
			if (!dc.isSpelKlaarGezet())
				btnNieuwSpel.setDisable(true);
		} catch (Exception e) {
			System.err.print(e);
		}
	}

	// INFO: sts____ == switchToScene_____

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
		FXMLLoader loader = new FXMLLoader(getClass().getResource("menuLogin.fxml"));
		Parent root = loader.load();

		MenuLoginController controller = loader.getController();
		controller.setDc(dc);

		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	public void switchToSceneInstellingen(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("menuInstellingen.fxml"));
		Parent root = loader.load();

		MenuInstellingenController controller = loader.getController();
		controller.setDc(dc);

		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	public void switchToSceneGameBord(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("gameBord.fxml"));
		Parent root = loader.load();

		gameBordController controller = loader.getController();
		controller.setDc(dc);

		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

	}

}
