package gui;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class WelkomScherm extends GridPane {
    Label lblWelkom;
    ImageView ivImage;
    
    public WelkomScherm() {

        buildGui();
        // Label aanmaken en op positie zetten
        
    }

    private void buildGui() {
        lblWelkom = new Label("Welkom bij JavaFX!!!");
//        lblWelkom.setLayoutX(200); // start 200px van links (boven)
//        lblWelkom.setLayoutY(10); // start 10px van boven (links)

        // Afbeelding aanmaken:
//        ivImage.setLayoutX(50);
//        ivImage.setLayoutY(50);

        // Voeg de elementen toe aan de scene.
       this.add(lblWelkom, 1, 1);
        
    }
}