package taalmanager;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class vertaal {

	private static ResourceBundle taal;

	public static void veranderTaal(String deTaal) {
		taal = ResourceBundle.getBundle("taalmanager/" + deTaal);

	}

	public static String geefWoord(String woord) {

		String value = null;

		try {
			value = taal.getString(woord);
		} catch (MissingResourceException e) {
			value = "Key not found: " + woord;
		}
		System.out.printf("Locale: %s, Value: %s %n", taal, value);

		return value;
	}
}
