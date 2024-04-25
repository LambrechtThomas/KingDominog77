package taalmanager;

import java.util.ResourceBundle;

public class vertaal {

	private static ResourceBundle taal;

	public static void veranderTaal(String deTaal) {
		taal = ResourceBundle.getBundle(deTaal);
	}

	public static String geefWoord(String woord) {
		return taal.getString(woord);
	}
}
