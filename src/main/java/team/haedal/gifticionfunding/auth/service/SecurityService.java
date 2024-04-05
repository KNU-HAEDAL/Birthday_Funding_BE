package team.haedal.gifticionfunding.auth.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;
import team.haedal.gifticionfunding.auth.dto.TokenDto;
import team.haedal.gifticionfunding.auth.jwt.JwtProvider;

@Service
@Slf4j
@RequiredArgsConstructor
public class SecurityService {
    private final JwtProvider jwtProvider;
    private final String prefix = "Bearer ";

    public TokenDto generateTokenDto(Long userId) {

        TokenDto tokenDto = jwtProvider.generateTokenDto(userId);

        return tokenDto;
    }

    public HttpHeaders setTokenHeaders(TokenDto tokenDto) {
        HttpHeaders headers = new HttpHeaders();
        ResponseCookie cookie = ResponseCookie.from("RefreshToken", tokenDto.getRefreshToken())
                .path("/")
                .maxAge(60*60*24*7)  // 쿠키 유효기간: 7일
                .secure(false)
                .sameSite("Lax")
                .httpOnly(true)
                .build();
        headers.add("Set-cookie", cookie.toString());
        headers.add("Authorization", prefix + tokenDto.getAccessToken());

        return headers;
    }


}
