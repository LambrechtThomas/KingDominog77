package resources;

import java.util.Locale;
import java.util.ResourceBundle;

public class TaalManager {

	// declareerd variable bundleN met waarde words want text files beginnen met
	// words
	private static final String BUNDLE_NAAM = "messages";

	private ResourceBundle bundle;

	public TaalManager() {
		kiesTaal(Locale.getDefault());
	}

	public void kiesTaal(Locale locale) {
		bundle = ResourceBundle.getBundle(BUNDLE_NAAM, locale);

	}

	public String getText(String key) {
		return bundle.getString(key);
	}

}
