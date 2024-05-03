package gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import DTO.spelerDTO;
import domein.DomeinController;
import domein.Kleur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

	// Dit is om de ListViews te populaten
	private ObservableList<spelerDTO> beschikbareSpelers;
	private ObservableList<spelerDTO> observableSpelerLijst;

	private DomeinController dc;

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

	public void initialize() {
		beschikbareSpelers = FXCollections.observableArrayList();
		observableSpelerLijst = FXCollections.observableArrayList();

		updateLabels();
		// haalGebruikersOp(); // TODO zeker

		// Gebruiker aanmaken
		btnMaakGebruiker.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				// Data uit fields ophalen
				String gebruikersnaam = fldGebruikersnaam.getText();
				String geboortedatum = fldGeboortedatum.getText();

				// Checken of de velden daadwerkelijk ingevuld zijn
				if (gebruikersnaam.isEmpty() || geboortedatum.isEmpty()) {
					dc.errorBox(vertaal.geefWoord("POPUP_MESSAGE_CREATION"), vertaal.geefWoord("POPUP_TITLE_CREATION"),
							vertaal.geefWoord("POPUP_MESSAGE_HEADER"));
				} else {
					// Try catch om na te gaan of de gebruiker in de database zit/geboortejaar een
					// cijfer is
					try {
						dc.registreerSpeler(gebruikersnaam, Integer.parseInt(geboortedatum));

						// Gelukt alert
						dc.doneBox(vertaal.geefWoord("CREATION_SUCCEED_MESSAGE"),
								vertaal.geefWoord("CREATION_SUCCEED_TITLE"),
								vertaal.geefWoord("CREATION_SUCCEED_HEADER"));

						haalGebruikersOp();
					} catch (NumberFormatException e) {
						System.err.print(e);

						// Niet gelukt cijfer alert
						dc.errorBox(vertaal.geefWoord("POPUP_MESSAGE_CREATION_NUMBER"),
								vertaal.geefWoord("POPUP_TITLE_CREATION"), vertaal.geefWoord("POPUP_MESSAGE_HEADER"));

					} catch (Exception e) {
						System.err.print(e);

						// Niet gelukt bestaand alert
						dc.errorBox(vertaal.geefWoord("POPUP_MESSAGE_CREATION_EXISTS"),
								vertaal.geefWoord("POPUP_TITLE_CREATION"), vertaal.geefWoord("POPUP_MESSAGE_HEADER"));
					}
				}
			}
		});

		// Gebruiker -> Speler
		btnAddAanSpelers.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				if (observableSpelerLijst.size() <= 3) {
					// Geselecteerd item uit de ListView in een variabele zetten
					spelerDTO selectedItem = lvGebruikers.getSelectionModel().getSelectedItem();

					// Gebruikers -> Spelers
					observableSpelerLijst.add(selectedItem);
					// Gebruiker uit de ObservableList deleten
					beschikbareSpelers.remove(selectedItem);

					// Updaten van de ListView van gebruikers
					updateSpelers();
				}
			}
		});

		// Speler -> Gebruiker
		btnRemoveVanSpelers.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				spelerDTO selectedItem = lvSpelers.getSelectionModel().getSelectedItem();

				// Verwijderen uit LV van Spelers
				observableSpelerLijst.remove(selectedItem);
				// Speler -> Gebruiker
				beschikbareSpelers.add(selectedItem);
				// update veranderingen
				updateSpelers();
			}
		});
	}

	public void setDc(DomeinController dc) {
		this.dc = dc;
		haalGebruikersOp();
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

		try {
			// clear de deelnames
			dc.clearDeelnemedeSpeler();
			// Vraag kleuren op
			ArrayList<Kleur> kleuren = new ArrayList<>(Arrays.asList(Kleur.values()));

			// Speler laten meedoen aan het spel met rando kleuren
			for (int i = 0; i < observableSpelerLijst.size(); i++) {
				dc.spelerDoetMee(observableSpelerLijst.get(i), kleuren.get(i));
			}

		} catch (Exception e) {
			System.err.println(e);
		}

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
	private void haalGebruikersOp() {
		// Clear de lijsten
		beschikbareSpelers.clear();
		observableSpelerLijst.clear();
		// Haal de gebruikers op
		// ObservableList maken
		beschikbareSpelers.addAll(dc.geefBeschikbareSpelers());
		// Haal de spelers op
		observableSpelerLijst.addAll(dc.getDeelnemendeSpelers());
		// spelers verwijderen uit gebruikers
		beschikbareSpelers.removeAll(observableSpelerLijst);
		// Laad ze ook in
		updateSpelers();
	}

	private void updateSpelers() {
		// Laad de ObservableList in de ListView
		lvGebruikers.setItems(beschikbareSpelers);
		lvSpelers.setItems(observableSpelerLijst);
	}

}
