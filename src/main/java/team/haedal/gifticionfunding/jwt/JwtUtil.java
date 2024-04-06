package team.haedal.gifticionfunding.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtil {
    //토큰 만료 검사
    public static boolean isExpired(String token, String secretKey){
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
                .getBody().getExpiration().before(new Date());
    }

    //멤버 Id 추출
    public static String extractMember(String token, String secretKey){
        String memberId = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token).getBody()
                .get("memberId").toString();

        return memberId;
    }
}
