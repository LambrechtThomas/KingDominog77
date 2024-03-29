package gui;

import domein.DomeinController;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class WelkomScherm extends GridPane {

	private Label lblWelkom;
	private boolean geefMelding;
	private DomeinController dc;

	public WelkomScherm(DomeinController dc) {
		this.dc = dc;
		buildGui();
		TweedeSchermTest tweedescherm = new TweedeSchermTest(dc, this);
	}

	private void buildGui() {

		setPadding(new Insets(20));

		lblWelkom = new Label("Welkom bij JavaFX!!!");

		Button btnStart = new Button("Start");
		btnStart.setText("Start");

		this.add(lblWelkom, 1, 1);
		this.add(btnStart, 1, 2);

		btnStart.setOnAction(event -> {
			// Create an alert object
			Alert alert = new Alert(Alert.AlertType.WARNING);

			TweedeSchermTest vs = new TweedeSchermTest(dc, this);
			this.getScene().setRoot(vs);

			alert.setTitle("[WIP] groep77");
			alert.setHeaderText("Programma nog in ontwikkeling");

			// Show the alert
			if (this.geefMelding) {
				this.geefMelding = true;
				alert.showAndWait();
			}

		});

	}
}