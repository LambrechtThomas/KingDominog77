package gui;

import java.io.IOException;
import java.util.ArrayList;

import DTO.spelerDTO;
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
	private int aantalSpelers;

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
	private GridPane grdBord;

	@FXML
	private Label lbAlgemeneTekst;

	@FXML
	private Label lbScore1;

	@FXML
	private Label lbScore2;

	@FXML
	private Label lbScore3;

	@FXML
	private Label lbScore4;

	@FXML
	private Label lbSpeler1;

	@FXML
	private Label lbSpeler2;

	@FXML
	private Label lbSpeler3;

	@FXML
	private Label lbSpeler4;

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

	public void setDc(DomeinController dc) {
		this.dc = dc;
		dc = null;
		
		try {
			aantalSpelers = dc.geefAantalSpelers();
			aantalSpelers = 3;
			
			startScene();
			
		} catch (Exception e) {
			//System.err.print(e);
			
			try {
				switchtoStart();
				System.out.println("Testing");
				System.out.println("Testing");
				System.out.println("Testing");
				System.out.println("Testing");
				System.out.println("Testing");
				System.out.println("Testing");
				System.out.println("Testing");
				System.out.println("Testing");
				System.out.println("Testing");
				
			} catch (IOException e1) {
				System.out.println("err");
			}
		}
	}

	private void startScene() {
		if (aantalSpelers == 3) {
			lbScore4.setDisable(true);
			lbScore4.setVisible(false);
			lbSpeler4.setDisable(true);
			lbSpeler4.setVisible(false);
		}
		
		try {
			ArrayList<spelerDTO> deelnemers = dc.geefDeelnemendeSpelersInSpel();
		} catch (Exception e) {
			System.err.println(e);
			//switchToSceneStart(null);
		}
			
		
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
	
	private void switchtoStart() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("menuStart.fxml"));
		Parent root = loader.load();

		MenuStartController controller = loader.getController();
		controller.setDc(dc);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}
