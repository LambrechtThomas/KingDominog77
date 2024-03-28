package resources;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class ResourceBundleEx {

	static public void main(String[] args) {

		Locale[] locales = { Locale.ENGLISH, new Locale("nl", "NL"), Locale.FRENCH };

		System.out.println("WELKOM:");
		for (Locale locale : locales) {
			getWord(locale, "WELKOM");
		}

		System.out.println("START:");
		for (Locale locale : locales) {
			getWord(locale, "START");
		}

		System.out.println("STOP:");
		for (Locale locale : locales) {
			getWord(locale, "STOP");
		}

	}

	static void getWord(Locale locale, String key) {
		ResourceBundle words = ResourceBundle.getBundle("resources/words", locale);
		String value;
		try {
			value = words.getString(key);
		} catch (MissingResourceException e) {
			value = "Key not found: " + key;
		}
		System.out.printf("Locale: %s, Value: %s %n", locale, value);
	}
}