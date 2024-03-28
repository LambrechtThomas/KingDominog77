package gui;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class WelkomScherm extends GridPane {

	private TweedeSchermTest tweedescherm;

	private Label lblWelkom;
	private ImageView ivImage;

	public WelkomScherm() {

		buildGui();

		tweedescherm = new TweedeSchermTest(this);

	}

	private void buildGui() {
		lblWelkom = new Label("Welkom bij JavaFX!!!");

		Button btnStart = new Button("Start");
		btnStart.setText("Start");

		this.add(lblWelkom, 1, 1);
		this.add(btnStart, 1, 2);

		btnStart.setOnAction(event -> {
			// Create an alert object
			Alert alert = new Alert(Alert.AlertType.INFORMATION);

			TweedeSchermTest vs = new TweedeSchermTest(this);
			this.getScene().setRoot(vs);

			alert.setTitle("Work in progress :)");
			alert.setHeaderText("Programma nog in ontwikkeling");
			alert.setContentText("Het is nog niet af.");

			// Show the alert
			alert.showAndWait();
		});

	}
}