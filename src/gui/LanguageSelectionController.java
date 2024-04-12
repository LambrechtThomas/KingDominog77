package gui;

import java.io.FileWriter;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import resources.Taal;
import taal.Language;

public class LanguageSelectionController {

	@FXML
	private Label titleLabel;

	@FXML
	private ComboBox<Language> languageComboBox;

	@FXML
	private Button saveButton;

	// Optional: Store the selected language (e.g., in a singleton class)
	private static Language chosenLanguage;

	@FXML
	public void initialize() {
		languageComboBox.getSelectionModel().selectFirst(); // Select first language by default
		updateLabels(); // Update labels based on current language
	}

	private void updateLabels() {
		titleLabel.setText(Taal.valueOf("SELECT_LANGUAGE").getLocaleString("Language")); // Example usage
	}

	@FXML
	public void saveLanguageSelection() {
		chosenLanguage = languageComboBox.getSelectionModel().getSelectedItem();

		// Implement logic to save the selected language (e.g., to a config file)
		saveLanguageToConfig(chosenLanguage); // Example using a saveLanguageToConfig method

		// Optionally, switch the application language based on chosenLanguage
		// updateApplicationLanguage(chosenLanguage); // Example using an
		// updateApplicationLanguage method

		// Close the language selection window
		Stage stage = (Stage) saveButton.getScene().getWindow();
		stage.close();
	}

	private void saveLanguageToConfig(Language chosenLanguage) {
		// Example: Write selected language code to a config file
		String languageCode = chosenLanguage.getLocale().getLanguage();
		try (FileWriter writer = new FileWriter("language.cfg")) {
			writer.write(languageCode);
		} catch (IOException e) {
			// Handle potential IO exceptions (e.g., log an error)
			System.err.println("Error saving language to config: " + e.getMessage());
		}
	}

	/*
	 * private void updateApplicationLanguage(Language chosenLanguage) { // Example:
	 * Update resource bundle based on chosen language
	 * ResourceBundle.setDefaultLocale(chosenLanguage.getLocale());
	 * 
	 * // Update labels and UI elements in other screens throughout the application
	 * // (This would involve iterating through UI elements and updating
	 * text/content // based on translations)
	 * 
	 * // Example (assuming methods exist to update labels in other screens):
	 * updateMainScreenLabels(); updateSettingsScreenLabels(); // ... update labels
	 * in other screens }
	 */

	// Optional: Static method to access chosen language throughout the application
	public static Language getChosenLanguage() {
		return chosenLanguage;
	}
}
