package team.haedal.gifticionfunding.global.error;

public class NotFoundUserException extends IllegalArgumentException {

    public NotFoundUserException() {
        super();
    }

    public NotFoundUserException(String message) {
        super(message);
    }

    public NotFoundUserException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundUserException(Throwable cause) {
        super(cause);
    }

}
