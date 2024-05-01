package gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;

import DTO.spelerDTO;
import domein.DomeinController;
import domein.Speler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
		gebruikersLijstUpdate();
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
    private ListView<spelerDTO> lvGebruikers;

    @FXML
    private ListView<spelerDTO> lvSpelers;

    public void initialize(){
    	updateLabels();
    	// Gebruiker aanmaken
    	btnMaakGebruiker.setOnAction(new EventHandler<ActionEvent>() {
    		public void handle(ActionEvent event) {
				// Data uit fields ophalen
    			String gebruikersnaam = fldGebruikersnaam.getText();
				String geboortedatum = fldGeboortedatum.getText();
				
				// Checken of de velden daadwerkelijk ingevuld zijn
				if(gebruikersnaam.isEmpty() || geboortedatum.isEmpty()) {
					dc.errorBox(vertaal.geefWoord("POPUP_MESSAGE_CREATION"), vertaal.geefWoord("POPUP_TITLE_CREATION"), vertaal.geefWoord("POPUP_MESSAGE_HEADER"));
				}
				else {
					// Try catch om na te gaan of de gebruiker in de database zit/geboortejaar een cijfer is
					try {
						dc.registreerSpeler(gebruikersnaam, Integer.parseInt(geboortedatum));
						
						// Gelukt alert
						dc.doneBox(vertaal.geefWoord("CREATION_SUCCEED_MESSAGE"), vertaal.geefWoord("CREATION_SUCCEED_TITLE"), vertaal.geefWoord("CREATION_SUCCEED_HEADER"));
						gebruikersLijstUpdate();
					} catch (NumberFormatException e) {
						System.err.print(e);
						
						// Niet gelukt cijfer alert
						dc.errorBox(vertaal.geefWoord("POPUP_MESSAGE_CREATION_NUMBER"), vertaal.geefWoord("POPUP_TITLE_CREATION"), vertaal.geefWoord("POPUP_MESSAGE_HEADER"));
						
					} catch (Exception e) {
						System.err.print(e);
						
						// Niet gelukt bestaand alert
						dc.errorBox(vertaal.geefWoord("POPUP_MESSAGE_CREATION_EXISTS"), vertaal.geefWoord("POPUP_TITLE_CREATION"), vertaal.geefWoord("POPUP_MESSAGE_HEADER"));
					}
				}
			}
    	});


    	
    }
    
    

    private void updateLabels() {
		btnAddAanSpelers.setText(vertaal.geefWoord("ADD"));
		btnMaakGebruiker.setText(vertaal.geefWoord("CREATE_USER"));
		btnNaarStart.setText(vertaal.geefWoord("BACK_TO_START"));
		btnRemoveVanSpelers.setText(vertaal.geefWoord("REMOVE"));
		lblConfiguratie.setText(vertaal.geefWoord("CONFIGURATION_GAME"));
		lblOnderConfiguratie.setText(vertaal.geefWoord("CONFIGURATION_SUBTXT"));
		lblGebruikersnaam.setText(vertaal.geefWoord("USERNAME"));
		lblGeboortedatum.setText(vertaal.geefWoord("DATE_OF_BIRTH"));
		
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
	
	// ListView laten vullen met gebruikers
    private void gebruikersLijstUpdate() {

    	
    	// Haal de gebruikers op
    	ArrayList<spelerDTO> beschikbareSpelerDTO = dc.geefBeschikbareSpelers();

    	// ObservableList maken
    	ObservableList<spelerDTO> beschikbareSpelers = FXCollections.observableArrayList(beschikbareSpelerDTO);
    	
    	// Laad de ObservableList in de ListView
    	lvGebruikers.setItems(beschikbareSpelers);
		
	}
	
}
