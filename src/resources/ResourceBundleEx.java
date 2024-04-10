package resources;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class ResourceBundleEx {

	static public void main(String[] args) {

	}

	public static String getWord(Locale locale, String key) {
		System.out.println(ResourceBundle.getBundle("resources/", locale).toString());
		ResourceBundle words = ResourceBundle.getBundle("resources/messages", locale);
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