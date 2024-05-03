package exceptions;

public class KleurBestaatNietException extends RuntimeException {
	public KleurBestaatNietException() {
		super("Geen geldige kleur door gegeven!");
	}

	public KleurBestaatNietException(String message) {
		super(message);
	}
}
