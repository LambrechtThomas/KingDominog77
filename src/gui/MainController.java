package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import resources.TaalManager;

public class MainController implements Initializable {

	private TaalManager taalManager;

	@FXML
	private Menu taalMenu;

	public MainController() {
		taalManager = new TaalManager();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

}
