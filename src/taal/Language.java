package taal;

import java.util.Locale;
import java.util.Map;

public enum Language {
	ENGLISH(Locale.US), DUTCH(new Locale("nl", "NL")), FRENCH(Locale.FRANCE);

	private final Locale locale;

	Language(Locale locale) {
		this.locale = locale;
	}

	public Locale getLocale() {
		return locale;
	}

	@Override
	public String toString() {
		return locale.getDisplayLanguage(); // Display language name based on locale
	}

	public static String getLocaleString(String key) {
		return getTranslatedText(key, Locale.getDefault());
	}

	/*public static String getTranslatedText(String key, Locale locale) {
		Map<String, String> languageTranslations = translations.get(locale.getLanguage());
		if (languageTranslations != null) {
			return languageTranslations.get(key);
		}
		return key; // Fallback to key if no translation found
	}

*/	}

}
