package exceptions;

public class SpelerDoetAlMeeException extends RuntimeException {
	
	public SpelerDoetAlMeeException() {
		super("Speler doet al mee met het spel");
	}

	public SpelerDoetAlMeeException(String message) {
		super(message);
	}
}
