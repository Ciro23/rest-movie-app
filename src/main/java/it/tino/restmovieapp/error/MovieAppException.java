package it.tino.restmovieapp.error;

import java.io.Serial;

public class MovieAppException extends RuntimeException {
	
	@Serial
    private static final long serialVersionUID = 6957668932886965668L;

    public MovieAppException() {
		super();
	}

	public MovieAppException(
		String message,
		Throwable cause,
		boolean enableSuppression,
		boolean writableStackTrace
	) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public MovieAppException(String message, Throwable cause) {
		super(message, cause);
	}

	public MovieAppException(String message) {
		super(message);
	}

	public MovieAppException(Throwable cause) {
		super(cause);
	}
}
