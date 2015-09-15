package co.sunny.exception;

public class GCRGException extends Exception {

	private static final long serialVersionUID = 2631865023457864220L;

	public GCRGException() {

	}

	public GCRGException(String message) {
		super(message);
	}

	public GCRGException(String message, Throwable cause) {
		super(message, cause);
	}

}
