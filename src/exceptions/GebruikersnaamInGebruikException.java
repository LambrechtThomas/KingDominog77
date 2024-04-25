package exceptions;

//exception voor als de gebruikersnaam al in gebruik is (kijkt na via spelerrepository)
public class GebruikersnaamInGebruikException extends RuntimeException {

	public GebruikersnaamInGebruikException() {
		super("Gebruikersnaam reeds in gebruik.");
	}

	public GebruikersnaamInGebruikException(String message) {
		super(message);
	}

}