package resources;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import taal.Language;

//Assuming T is a class that handles translation logic
public class Taal {

	private static final Map<String, Map<String, String>> translations = loadTranslations();

	private static List<Locale> getAvailableLocales() {
		List<Locale> locales = new ArrayList<>();
		locales.add(new Locale("nl", "NL")); // Dutch
		locales.add(Locale.FRENCH); // French
		locales.add(Locale.US); // English
		return locales;
	}

	// Load translations from resource bundles (implementation omitted)
	private static Map<String, Map<String, String>> loadTranslations() {

		// ...

		for (Locale locale : getAvailableLocales()) {
			ResourceBundle bundle = ResourceBundle.getBundle("messages", locale);
			Map<String, String> languageTranslations = new HashMap<>();
			for (Enumeration<String> keys = bundle.getKeys(); keys.hasMoreElements();) {
				String key = keys.nextElement();
				String value = bundle.getString(key);
				languageTranslations.put(key, value);
			}
			translations.put(locale.getLanguage(), languageTranslations);
		}
		return translations;
	}

	public static Language valueOf(String key) {
		for (Language language : Language.values()) {
			if (language.name().equals(key)) {
				return language;
			}
		}
		throw new IllegalArgumentException("Invalid language key: " + key);
	}

	public static String getTranslatedText(String key, Locale locale) {
		Map<String, String> languageTranslations = translations.get(locale.getLanguage());
		if (languageTranslations != null) {
			return languageTranslations.get(key);
		}
		return key; // Fallback to key if no translation found
	}

}
