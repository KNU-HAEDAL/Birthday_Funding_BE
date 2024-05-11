package team.haedal.gifticionfunding.global.error;

public class NotFoundFriendRequestException extends IllegalArgumentException {

    public NotFoundFriendRequestException() {
        super();
    }

    public NotFoundFriendRequestException(String message) {
        super(message);
    }

    public NotFoundFriendRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundFriendRequestException(Throwable cause) {
        super(cause);
    }

}
