package cloudwalk.quakeparser.usecase.exception;

public class UnreadableFileException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private static final String MESSAGE = "Not possible to read the file.";

    public UnreadableFileException(final Throwable cause) {
        super(MESSAGE, cause);
    }
}
