package resources;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class TaalManager {

	private final Map<String, ResourceBundle> bundles;
	private String currentLanguage;

	public TaalManager() {
		bundles = new HashMap<>();
		bundles.put("nl", ResourceBundle.getBundle("nl"));
		bundles.put("en", ResourceBundle.getBundle("en"));
		bundles.put("fr", ResourceBundle.getBundle("fr"));
		currentLanguage = "nl";
	}

	public void changeLanguage(String languageCode) {
		if (bundles.containsKey(languageCode)) {
			currentLanguage = languageCode;
		}
	}

	public String getTranslation(String key) {
		return bundles.get(currentLanguage).getString(key);
	}

	public List<String> getSupportedLanguages() {
		return new ArrayList<>(bundles.keySet());
	}

}