package gui;

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

}
