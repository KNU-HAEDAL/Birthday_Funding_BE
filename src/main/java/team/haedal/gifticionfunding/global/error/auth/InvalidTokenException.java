package team.haedal.gifticionfunding.global.error.auth;

import io.jsonwebtoken.ExpiredJwtException;

public class InvalidTokenException extends ExpiredJwtException {

    public InvalidTokenException(String message) {
        super(null, null, message);
    }

}
