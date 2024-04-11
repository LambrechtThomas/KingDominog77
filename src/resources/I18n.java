package resources;

import java.util.Locale;
import java.util.ResourceBundle;

public class I18n {
<<<<<<< Updated upstream
	private static final String BUNDLE_NAME = "messages_en_US.properties";
=======
	private static final String BUNDLE_NAME = "messages_en_GB.properties";
>>>>>>> Stashed changes

	private ResourceBundle bundle;

	public I18n() {
		Locale locale = Locale.getDefault();
		bundle = ResourceBundle.getBundle(BUNDLE_NAME, locale);
	}

	public String get(String key) {
		return bundle.getString(key);
	}

}
