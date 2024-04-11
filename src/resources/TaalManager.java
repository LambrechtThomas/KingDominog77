package resources;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class TaalManager {

	private static final String BUNDLE_NAAM = "messages";
	private ResourceBundle bundle;

	public TaalManager() {
		kiesTaal(Locale.getDefault());
	}

	public void kiesTaal(Locale locale) {
		try {
			bundle = ResourceBundle.getBundle(BUNDLE_NAAM, locale);
		} catch (MissingResourceException e) {
			// Handle missing resource bundle (e.g., log a warning)
			System.err.println("Missing resource bundle for locale: " + locale);
		}
	}

	public String getText(String sleutel) {
		return bundle.getString(sleutel);
	}

}
/*
 * private final Map<String, ResourceBundle> bundles; private String
 * currentLanguage; // private static final String BUNDLE_NAAM = "messages"; //
 * private ResourceBundle bundle;
 * 
 * public TaalManager() { bundles = new HashMap<>(); bundles.put("nl",
 * ResourceBundle.getBundle("nl")); bundles.put("en",
 * ResourceBundle.getBundle("en")); bundles.put("fr",
 * ResourceBundle.getBundle("fr")); currentLanguage = "nl"; }
 * 
 * public void changeLanguage(String languageCode) { if
 * (bundles.containsKey(languageCode)) { currentLanguage = languageCode; } }
 * 
 * public String getTranslation(String key) { return
 * bundles.get(currentLanguage).getString(key); }
 * 
 * public List<String> getSupportedLanguages() { return new
 * ArrayList<>(bundles.keySet()); }
 */