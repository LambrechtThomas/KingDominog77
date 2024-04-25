package exceptions;

public class SpelerBestaatNietException extends RuntimeException {

	public SpelerBestaatNietException() {
		super("Speler bestaat niet!");
	}

	public SpelerBestaatNietException(String message) {
		super(message);
	}
}
