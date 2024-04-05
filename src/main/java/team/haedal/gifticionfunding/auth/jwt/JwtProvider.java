package team.haedal.gifticionfunding.auth.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import team.haedal.gifticionfunding.auth.dto.TokenDto;
import team.haedal.gifticionfunding.global.error.auth.InvalidTokenException;

import java.security.Key;
import java.util.Date;

@Component
public class JwtProvider {
    private final Key key;
    private static final String GRANT_TYPE = "Bearer";
    private static final long ACCESS_TOKEN_EXPIRES_IN = 1000L * 60 * 30; //access 30분
    private static final long REFRESH_TOKEN_EXPIRES_IN = 1000L * 60 * 60 * 24 * 14; //refresh 14일

    public JwtProvider(@Value("${jwt.secret}") String secretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public TokenDto generateTokenDto(Long userId) {

        long now = (new Date()).getTime();

        String accessToken = generateAccessToken(now, userId);
        String refreshToken = generateRefreshToken(now);

        return TokenDto.builder()
                .grantType(GRANT_TYPE)
                .accessToken(accessToken)
                .accessTokenExpiresIn(new Date(now + ACCESS_TOKEN_EXPIRES_IN).getTime())
                .refreshToken(refreshToken)
                .build();
    }

    public String generateAccessToken(long now, Long userId) {
        String accessToken = Jwts.builder()
                .setSubject(String.valueOf(userId))
                .setExpiration(new Date(now + ACCESS_TOKEN_EXPIRES_IN))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();

        return accessToken;
    }

    public String generateRefreshToken(long now) {
        String refreshToken = Jwts.builder()
                .setExpiration(new Date(now + REFRESH_TOKEN_EXPIRES_IN))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();

        return refreshToken;
    }

    public boolean validateToken(String token) {
        try{
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (SecurityException | MalformedJwtException | ExpiredJwtException | UnsupportedJwtException |
                 IllegalArgumentException e) {
            throw e;
        }
    }

    public String parseAccessToken(String authHeader) {
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring("Bearer ".length());
            return token;
        } else {
            throw new InvalidTokenException("AccessToken이 없거나 Bearer type이 아닙니다.");
        }
    }

    public long getUserIdFromToken(String accessToken) {
        String userId = parseClaims(accessToken).getSubject();

        return Long.parseLong(userId);
    }

    protected Claims parseClaims(String accessToken) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(accessToken)
                    .getBody();
        } catch (ExpiredJwtException e) {
            throw e;
        }
    }


}
