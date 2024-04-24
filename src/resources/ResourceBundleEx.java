package resources;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class ResourceBundleEx {

	private Locale taal;

	public ResourceBundleEx() {
		this.taal = new Locale("nl", "NL");
	}

	public String getWord(String key) {
		ResourceBundle messages = ResourceBundle.getBundle("resources/messages", this.taal);
		String value;
		try {
			value = messages.getString(key);
		} catch (MissingResourceException e) {
			value = "Key not found: " + key;
		}
		System.out.printf("Locale: %s, Value: %s %n", this.taal, value);
		return value;
	}

	public void veranderTaal() {
		this.taal = Locale.ENGLISH;
		System.out.println("veranderd taal");
	}
}
