package gui;

import java.io.IOException;

import domein.DomeinController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import taalmanager.vertaal;

public class gameBordController {
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	private DomeinController dc;
	
	public void setDc(DomeinController dc) {
		this.dc = dc;
	}
	
	@FXML
	private GridPane grdBord;

	@FXML
	private Button btnDraai;

	@FXML
	private Button btnRoteer;

	@FXML
	private Button btnSpeelveldSpelerDrie;

	@FXML
	private Button btnSpeelveldSpelerEen;

	@FXML
	private Button btnSpeelveldSpelerTwee;

	@FXML
	private Label lblPlayingAction;

	@FXML
	private Label lblPlayingUsername;

	@FXML
	private Label lblProgressie;

	@FXML
	private ProgressBar pbProgressie;

	public void initialize() {

		btnDraai.setText(vertaal.geefWoord("TURN_RIGHT"));

		btnRoteer.setText(vertaal.geefWoord("MIRROR"));

		btnSpeelveldSpelerDrie.setText(vertaal.geefWoord("PLAYER_3"));

		btnSpeelveldSpelerEen.setText(vertaal.geefWoord("PLAYER_1"));

		btnSpeelveldSpelerTwee.setText(vertaal.geefWoord("PLAYER_2"));

		lblProgressie.setText(vertaal.geefWoord("ALMOST_YOUR_TURN"));

		lblPlayingUsername.setText(vertaal.geefWoord("IS_PLAYING"));

	}

	@FXML
	public void switchToSceneStart(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("menuStart.fxml"));
		Parent root = loader.load();
		
		MenuStartController controller = loader.getController();
		controller.setDc(dc);
		
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}
