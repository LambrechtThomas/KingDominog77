package gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

import DTO.spelerDTO;
import domein.DomeinController;
import domein.Kleur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import taalmanager.vertaal;

public class MenuLoginController extends AnchorPane {

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

	public MenuLoginController(DomeinController dc2, Stage stage) {
		loadFxmlScreen("menuLogin.fxml");
		this.dc = dc2;
		this.stage = stage;

		System.out.println(dc);

		updateLabels();
		haalGebruikersOp();
		updateSpelers();

	}

	// ======================================================

	// Dit is om de ListViews te populaten
	private ObservableList<spelerDTO> beschikbareSpelers;
	private ObservableList<spelerDTO> observableSpelerLijst;

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

		// Gebruiker aanmaken
		btnMaakGebruiker.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				// Data uit fields ophalen
				String gebruikersnaam = fldGebruikersnaam.getText();
				String geboortedatum = fldGeboortedatum.getText();

				// Checken of de velden daadwerkelijk ingevuld zijn
				if (gebruikersnaam.isEmpty() || geboortedatum.isEmpty()) {
					DomeinController.errorBox(vertaal.geefWoord("POPUP_MESSAGE_CREATION"),
							vertaal.geefWoord("POPUP_TITLE_CREATION"), vertaal.geefWoord("POPUP_MESSAGE_HEADER"));
				} else {
					// Try catch om na te gaan of de gebruiker in de database zit/geboortejaar een
					// cijfer is
					try {
						dc.registreerSpeler(gebruikersnaam, Integer.parseInt(geboortedatum));

						// Gelukt alert
						DomeinController.doneBox(vertaal.geefWoord("CREATION_SUCCEED_MESSAGE"),
								vertaal.geefWoord("CREATION_SUCCEED_TITLE"),
								vertaal.geefWoord("CREATION_SUCCEED_HEADER"));

						haalGebruikersOp();
					} catch (NumberFormatException e) {
						System.err.print(e);

						// Niet gelukt cijfer alert
						DomeinController.errorBox(vertaal.geefWoord("POPUP_MESSAGE_CREATION_NUMBER"),
								vertaal.geefWoord("POPUP_TITLE_CREATION"), vertaal.geefWoord("POPUP_MESSAGE_HEADER"));

					} catch (Exception e) {
						System.err.print(e);

						// Niet gelukt bestaand alert
						DomeinController.errorBox(vertaal.geefWoord("POPUP_MESSAGE_CREATION_EXISTS"),
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

		btnNaarStart.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {

				MenuStartController scene = new MenuStartController(dc, stage);
				getScene().setRoot(scene);

				try {
					// clear de deelnames
					dc.clearDeelnemedeSpeler();
					// Vraag kleuren op
					ArrayList<Kleur> kleuren = new ArrayList<>(dc.geefBeschikbareKleuren());

					// Speler laten meedoen aan het spel met rando kleuren
					for (int i = 0; i < observableSpelerLijst.size(); i++) {
						dc.spelerDoetMee(observableSpelerLijst.get(i), kleuren.get(i));
					}

				} catch (Exception e) {
					System.err.println(e);
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

	// ListView laten vullen met gebruikers
	private void haalGebruikersOp() {
		// Clear de lijsten
		beschikbareSpelers.clear();
		observableSpelerLijst.clear();
		// Haal de gebruikers op
		beschikbareSpelers.addAll(dc.geefBeschikbareSpelers());
		// Haal de spelers op
		observableSpelerLijst.addAll(dc.getDeelnemendeSpelers());
		// Laad ze ook in
		ArrayList<String> namen = (ArrayList<String>) observableSpelerLijst.stream().map(v -> v.gebruikersnaam())
				.collect(Collectors.toList());
		beschikbareSpelers.removeAll(beschikbareSpelers.stream().filter(v -> namen.contains(v.gebruikersnaam()))
				.collect(Collectors.toList()));
		updateSpelers();


		System.out.printf("gebruikers %s %n", beschikbareSpelers);
		System.out.printf("spelers %s %n", observableSpelerLijst);
		System.out.printf("%n%n%n");

	}

	private void updateSpelers() {
		// Laad de ObservableList in de ListView
		lvGebruikers.setItems(beschikbareSpelers);
		lvSpelers.setItems(observableSpelerLijst);
	}

}
