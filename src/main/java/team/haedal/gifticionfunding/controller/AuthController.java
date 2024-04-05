package team.haedal.gifticionfunding.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/")
@Tag(name="Auth",description="회원가입/로그인 관련 API")
public class AuthController {
    @PostMapping( "/auth")
    @Operation(summary = "카카오 로그인 및 회원가입 API")
    public RedirectView redirectKakao() {
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/oauth2/authorization/kakao");
        return redirectView;
    }
}
