package gui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import taalmanager.vertaal;

public class MenuInstellingenController {

	private Stage stage;
	private Scene scene;
	private Parent root;
	  	@FXML
	    private Button btnEnglish;

	    @FXML
	    private Button btnFrancais;

	    @FXML
	    private Button btnNederlands;
	    
	    @FXML
	    private Button btnTerugNaarStart;
	
	public void initialize() {

		btnEnglish.setText(vertaal.geefWoord("ENGLISH"));

		btnNederlands.setText(vertaal.geefWoord("NEDERLANDS"));

		btnFrancais.setText(vertaal.geefWoord("FRANCAIS"));
		
		
		btnTerugNaarStart.setText(vertaal.geefWoord("BACK_TO_START"));
	}

	public void switchToSceneStart(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("menuStart.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}
