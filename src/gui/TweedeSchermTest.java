package gui;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class TweedeSchermTest extends VBox {

	private Label lblWelkom;
	private Button btnTerug;
	private String inlognaam;
	private WelkomScherm welkomScherm;

	public TweedeSchermTest(WelkomScherm welkomScherm) {
		this.welkomScherm = welkomScherm;
		buildGui();
	}

	private void buildGui() {
		setSpacing(10);
		setPadding(new Insets(20));

		lblWelkom = new Label(String.format("You successfully logged in %s! ", "Mauro"));
		btnTerug = new Button("Back");
		Region spring = new Region();
		HBox.setHgrow(spring, Priority.ALWAYS);
		this.getChildren().addAll(lblWelkom, spring, btnTerug);

		btnTerug.setOnAction(evt -> {
			this.getScene().setRoot(welkomScherm);
		});
	}
}