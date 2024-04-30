package gui;

import java.io.IOException;

import domein.DomeinController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import taalmanager.vertaal;

public class MenuLoginController {

	private Stage stage;
	private Scene scene;
	private Parent root;
	
	private DomeinController dc;
	
	public void setDc(DomeinController dc) {
		this.dc = dc;
	}

    @FXML
    private Button btnAddAanSpelers;

    @FXML
    private Button btnMaakGebruiker;

    @FXML
    private Button btnNaarStart;

    @FXML
    private Button btnRemoveVanSpelers;

    @FXML
    private TextField fldGeboortedatum;

    @FXML
    private TextField fldGebruikersnaam;

    @FXML
    private Label lblConfiguratie;

    @FXML
    private Label lblGeboortedatum;

    @FXML
    private Label lblGebruikersnaam;

    @FXML
    private Label lblOnderConfiguratie;

    @FXML
    private ListView<?> lvGebruikers;

    @FXML
    private ListView<?> lvSpelers;

    public void initialize(){
    	updateLabels();
    }

    private void updateLabels() {
		btnAddAanSpelers.setText(vertaal.geefWoord("ADD"));
		btnMaakGebruiker.setText(vertaal.geefWoord("CREATE_USER"));
		btnNaarStart.setText(vertaal.geefWoord("BACK_TO_START"));
		btnRemoveVanSpelers.setText(vertaal.geefWoord("REMOVE"));
		lblConfiguratie.setText(vertaal.geefWoord("CONFIGURATION_GAME"));
		lblOnderConfiguratie.setText(vertaal.geefWoord("CONFIGURATION_SUBTXT"));
		
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
