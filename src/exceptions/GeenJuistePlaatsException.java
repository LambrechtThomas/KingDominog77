package exceptions;

public class GeenJuistePlaatsException extends RuntimeException {
	public GeenJuistePlaatsException() {
		super("De domino hangt niet aan juiste landschap of koningsTegel");
	}

	public GeenJuistePlaatsException(String message) {
		super(message);
	}
}
