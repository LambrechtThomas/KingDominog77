package gui;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;


public class WelkomScherm extends GridPane {
    Label lblWelkom;
    ImageView ivImage;
    
    public WelkomScherm() {

        buildGui();
        // Label aanmaken en op positie zetten
        
    }

    private void buildGui() {
        lblWelkom = new Label("Welkom bij JavaFX!!!");
        
        
        Button btnStart = new Button("Start");
        btnStart.setText("Start");
//        lblWelkom.setLayoutX(200); // start 200px van links (boven)
//        lblWelkom.setLayoutY(10); // start 10px van boven (links)

        // Afbeelding aanmaken:
//        ivImage.setLayoutX(50);
//        ivImage.setLayoutY(50);

        // Voeg de elementen toe aan de scene.
       this.add(lblWelkom, 1, 1);
       this.add(btnStart, 1, 2);
        
       btnStart.setOnAction(event -> {
           // Create an alert object
           Alert alert = new Alert(Alert.AlertType.INFORMATION);

           alert.setTitle("Work in progress :)");
           alert.setHeaderText("Programma nog in ontwikkeling");
           alert.setContentText("Het is nog niet af.");

           // Show the alert
           alert.showAndWait();
       });
    }
}