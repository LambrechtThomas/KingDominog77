package gui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;

public class gameBordController {
	private Stage stage;
	private Scene scene;
	private Parent root;

	@FXML
	private Button btn00;

	@FXML
	private Button btn01;

	@FXML
	private Button btn02;

	@FXML
	private Button btn03;

	@FXML
	private Button btn04;

	@FXML
	private Button btn10;

	@FXML
	private Button btn11;

	@FXML
	private Button btn12;

	@FXML
	private Button btn13;

	@FXML
	private Button btn14;

	@FXML
	private Button btn20;

	@FXML
	private Button btn21;

	@FXML
	private Button btn22King;

	@FXML
	private Button btn23;

	@FXML
	private Button btn24;

	@FXML
	private Button btn30;

	@FXML
	private Button btn31;

	@FXML
	private Button btn32;

	@FXML
	private Button btn33;

	@FXML
	private Button btn34;

	@FXML
	private Button btn40;

	@FXML
	private Button btn41;

	@FXML
	private Button btn42;

	@FXML
	private Button btn43;

	@FXML
	private Button btn44;

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

	@FXML
	public void switchToSceneStart(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("menuStart.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}
