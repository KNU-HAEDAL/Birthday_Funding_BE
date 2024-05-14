package team.haedal.gifticionfunding.global.error;

public class NotFoundGifticonException extends IllegalArgumentException{
    public NotFoundGifticonException() {
        super();
    }

    public NotFoundGifticonException(String message) {
        super(message);
    }

    public NotFoundGifticonException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundGifticonException(Throwable cause) {
        super(cause);
    }
}
