package resources;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class ResourceBundleEx {

	static public void main(String[] args) {

		Locale[] locales = { Locale.ENGLISH, new Locale("nl", "NL"), Locale.FRENCH };

	}

	public static String getWord(Locale locale, String key) {
		ResourceBundle words = ResourceBundle.getBundle("resources/words", locale);
		String value;
		try {
			value = words.getString(key);
		} catch (MissingResourceException e) {
			value = "Key not found: " + key;
		}
		System.out.printf("Locale: %s, Value: %s %n", locale, value);
		return value;
	}
}