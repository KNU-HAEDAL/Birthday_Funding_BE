package team.haedal.gifticionfunding.oauth2;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import team.haedal.gifticionfunding.jwt.JwtProvider;
import team.haedal.gifticionfunding.jwt.TokenDto;

import java.io.IOException;
import java.util.Map;


@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler{
    private final JwtProvider jwtProvider;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        // jwt 발급 및 리다이렉트
        OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();
        Map<String, Object> attributes = oauth2User.getAttributes();
        Map<String, String> properties = (Map<String, String>) attributes.get("properties");
        String memberId = properties.get("nickname");

        String tokenDto = jwtProvider.createJwt(memberId);


        response.sendRedirect("http://localhost:8080/"+tokenDto);
    }
}