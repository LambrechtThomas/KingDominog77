package gui;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class TaalAanpasScherm extends GridPane {

	private Label lblWelkom;
	private Button btnTerug;
	private TweedeSchermTest tweedeSchermTest;

	public TaalAanpasScherm(TweedeSchermTest TweedeSchermTest) {
		this.tweedeSchermTest = TweedeSchermTest;
		buildGui();
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

		// Knop aanmaken
		Button btnTaalNL = new Button("Nederlands");
		btnTaalNL.setOnAction(evt -> {
			// Taal aanpassen
			this.getScene().setRoot(tweedeSchermTest);
		});
		// Knop aanmaken
		Button btnTaalFR = new Button("Frans");
		btnTaalFR.setOnAction(evt -> {
			// Taal aanpassen
			this.getScene().setRoot(tweedeSchermTest);
		});
		// Knop aanmaken
		Button btnTaalEN = new Button("Engels");
		btnTaalEN.setOnAction(evt -> {
			// Taal aanpassen
			this.getScene().setRoot(tweedeSchermTest);
		});

		this.add(btnTaalNL, 0, 0);
		this.add(btnTaalFR, 1, 0);
		this.add(btnTaalEN, 2, 0);

	}
}