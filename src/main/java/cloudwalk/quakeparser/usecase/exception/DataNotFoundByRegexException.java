package cloudwalk.quakeparser.usecase.exception;

public class DataNotFoundByRegexException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private static final String MESSAGE =
            "Player data not found with a configured expression.";
    public DataNotFoundByRegexException(final Throwable cause) {
        super(MESSAGE, cause);
    }

}
