package gui;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class WelkomScherm extends Pane {
	public WelkomScherm() {

		// Label aanmaken en op positie zetten
		Label lblWelkom = new Label("Welkom bij JavaFX!!!");
		lblWelkom.setLayoutX(200); // start 200px van links (boven)
		lblWelkom.setLayoutY(10); // start 10px van boven (links)

		// Afbeelding aanmaken:
		ImageView ivImage = new ImageView(new Image(getClass().getResourceAsStream("/images/bug.png")));
		ivImage.setLayoutX(50);
		ivImage.setLayoutY(50);

		// Voeg de elementen toe aan de scene.
		getChildren().addAll(lblWelkom, ivImage);
	}
}