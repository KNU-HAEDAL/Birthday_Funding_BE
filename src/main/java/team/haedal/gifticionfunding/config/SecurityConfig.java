package team.haedal.gifticionfunding.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import team.haedal.gifticionfunding.jwt.JwtProvider;
import team.haedal.gifticionfunding.oauth2.OAuth2SuccessHandler;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final OAuth2UserService oAuth2UserService;
    private final OAuth2SuccessHandler oAuth2SuccessHandler;
    @Value("${jwt.secret}")
    private String secretKey;


    private static final String[] WHITE_LIST = {
            "/api/auth/**",
            "/api-docs/**",
            "/login/**",
    };

    private static final String[] AUTHENTICATION_LIST = {
            "/api/gifticon"
    };

    @Bean
    protected SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizeRequest -> authorizeRequest
                        .requestMatchers(WHITE_LIST).permitAll()
                        .anyRequest().authenticated()
                )
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
                .addFilterBefore(new JwtFilterConfig(secretKey), UsernamePasswordAuthenticationFilter.class)
                .oauth2Login(oauth2Login -> oauth2Login
                        .successHandler(oAuth2SuccessHandler)
                        .userInfoEndpoint(userInfoEndpoint ->
                                userInfoEndpoint.userService(oAuth2UserService))
                );
        return httpSecurity.build();
    }
}