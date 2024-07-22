package it.tino.postgres;

import java.io.Serial;

public class DaoException extends RuntimeException {
	
	@Serial
    private static final long serialVersionUID = 6957668932886965668L;

    public DaoException() {
		super();
	}

	public DaoException(
		String message,
		Throwable cause,
		boolean enableSuppression,
		boolean writableStackTrace
	) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public DaoException(String message, Throwable cause) {
		super(message, cause);
	}

	public DaoException(String message) {
		super(message);
	}

	public DaoException(Throwable cause) {
		super(cause);
	}
}
