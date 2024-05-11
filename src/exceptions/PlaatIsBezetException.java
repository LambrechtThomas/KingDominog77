package exceptions;

public class PlaatIsBezetException extends RuntimeException {
	public PlaatIsBezetException() {
		super("Plaats is al in bezet!");
	}

	public PlaatIsBezetException(String message) {
		super(message);
	}
}
