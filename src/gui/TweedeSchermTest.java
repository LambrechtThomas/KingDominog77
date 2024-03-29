package gui;

import domein.DomeinController;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class TweedeSchermTest extends GridPane {

	private Label lblWelkom;
	private Button btnTerug;
	private WelkomScherm welkomScherm;
	private DomeinController dc;

	public TweedeSchermTest(DomeinController dc, WelkomScherm welkomScherm) {
		this.dc = dc;
		this.welkomScherm = welkomScherm;
		buildGui();

//		FXMLLoader loader = new FXMLLoader(getClass().getResource("Intermission.fxml"));
//		loader.setRoot(this);
//		loader.setController(this);
//		try {
//			loader.load();
//		} catch (IOException ex) {
//			throw new RuntimeException(ex);
//		}
	}

	private void buildGui() {

		this.setPadding(new Insets(20));
		this.setHgap(20);
		this.setVgap(20);
		this.setGridLinesVisible(false);
		ColumnConstraints col1 = new ColumnConstraints();
		col1.setHgrow(Priority.ALWAYS);
		ColumnConstraints col2 = new ColumnConstraints();
		this.getColumnConstraints().addAll(col1, col2);

		// Eerste rij
		Label lblTitle1 = new Label("Taal aanpassen");
		Button btnButton1 = new Button("Nederlands");
		btnButton1.setOnAction(evt -> {
//			this.getScene().setRoot(welkomScherm);
			TaalAanpasScherm vs = new TaalAanpasScherm(this);
			this.getScene().setRoot(vs);
		});
		this.add(lblTitle1, 0, 0);
		this.add(btnButton1, 1, 0);

		// Tweede rij
		Label lblTitle2 = new Label("Stop toepassing");
		Button btnButton2 = new Button("EXIT");
		btnButton2.setOnAction(evt -> {

			Platform.exit();

		});
		this.add(lblTitle2, 0, 1);
		this.add(btnButton2, 1, 1);

		// Derde rij
		Label lblTitle3 = new Label("Start het spel");
		Button btnButton3 = new Button("Start Game");
		btnButton3.setOnAction(evt -> {
			lblTitle3.setText("Start het spel :: Het spel zou nu moeten starten.");
			btnButton3.setText("Gestart");
			btnButton3.setDisable(true);
		});
		this.add(lblTitle3, 0, 2);
		this.add(btnButton3, 1, 2);

		// Vierde rij
		Label lblTitle4 = new Label("Gebruiker toevoegen");
		TextField txfTitle4 = new TextField();
		Button btnButton4 = new Button("Add");
		Button btnButton4_2 = new Button("Add new player");
		btnButton4_2.setVisible(false);

		btnButton4.setOnAction(evt -> {
			btnButton4.setVisible(false);
			txfTitle4.setDisable(true);
			dc.registreerSpeler(txfTitle4.getText(), 2005);

			txfTitle4.setText(String.format("%s is toegevoegd aan de database.", txfTitle4.getText()));

			btnButton4_2.setVisible(true);
//			this.getScene().setRoot(welkomScherm);
		});

		btnButton4_2.setOnAction(evt -> {
			txfTitle4.setText("");
			btnButton4_2.setVisible(false);
			btnButton4.setVisible(true);
			txfTitle4.setDisable(false);
		});

		this.add(lblTitle4, 0, 3);
		this.add(txfTitle4, 1, 3);
		this.add(btnButton4, 2, 3);
		this.add(btnButton4_2, 2, 3);

		// Vijfde rij
		Label lblTitle5 = new Label("Beta V1");
		Button btnButton5 = new Button("Terug");
		btnButton5.setOnAction(evt -> {
			this.getScene().setRoot(welkomScherm);

		});
		this.add(lblTitle5, 1, 4);
		this.add(btnButton5, 0, 4);

//		lblWelkom = new Label("Voorbeeld van een 2de scherm");
//		btnTerug = new Button("Back");
//		Region spring = new Region();
//		HBox.setHgrow(spring, Priority.ALWAYS);
//		this.getChildren().addAll(lblWelkom, spring, btnTerug);
//
//		btnTerug.setOnAction(evt -> {
//			this.getScene().setRoot(welkomScherm);
//		});
	}

}