package gui;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import resources.TaalManager;

public class MainController implements Initializable {

	private TaalManager taalManager;

	@FXML
	private Menu taalMenu;

	public MainController() {
		taalManager = new TaalManager();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		bouwTaalMenu();
	}

	private void bouwTaalMenu() {
		for (Locale locale : Locale.getAvailableLocales()) {
			MenuItem menuItem = new MenuItem(locale.getDisplayLanguage());
			menuItem.setOnAction(event -> taalManager.kiesTaal(locale));
			taalMenu.getItems().add(menuItem);
		}
	}

}
