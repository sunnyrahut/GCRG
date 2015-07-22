package co.sunny.exception;

public class EateryException extends Exception {

	private static final long serialVersionUID = 2631865023457864220L;

	public EateryException() {

	}

	public EateryException(String message) {
		super(message);
	}

	public EateryException(String message, Throwable cause) {
		super(message, cause);
	}

}
