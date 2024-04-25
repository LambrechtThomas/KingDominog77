package exceptions;

public class SpelBestaatNietException extends RuntimeException {

	public SpelBestaatNietException() {
		super("Spel bestaat nog niet!");
	}

	public SpelBestaatNietException(String message) {
		super(message);
	}
}

