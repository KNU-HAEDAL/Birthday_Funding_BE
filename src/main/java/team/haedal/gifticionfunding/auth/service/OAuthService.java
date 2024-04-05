package team.haedal.gifticionfunding.auth.service;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import team.haedal.gifticionfunding.dto.user.request.UserCreate;

@Service
@Slf4j
@RequiredArgsConstructor
@PropertySource("classpath:application.yml")
public class OAuthService {
    @Value("${kakao.client-id}")
    private String CLIENT_ID;
    @Value("${kakao.redirect-uri}")
    private String REDIRECTION_URI;
    @Value("${kakao.token-uri}")
    private String TOKEN_URI;

    @Value("${kakao.user-info-uri}")
    private String USER_INFO_URI;

    private final RestTemplate restTemplate = new RestTemplate();


    public UserCreate getUserInfo(String code) {
        String accessToken = getAccessToken(code);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        HttpEntity entity = new HttpEntity(headers);

        //restTemplate.exchange(USER_INFO_URI,HttpMethod.GET, entity, JsonNode.class).getBody();
        ResponseEntity<JsonNode> responseNode = restTemplate.exchange(USER_INFO_URI, HttpMethod.GET, entity, JsonNode.class);
        JsonNode userInfoNode = responseNode.getBody();
        return UserCreate.from(userInfoNode);
    }

    public String getAccessToken(String code) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", CLIENT_ID);
        params.add("redirect_uri", REDIRECTION_URI);
        params.add("code", code);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity entity=new HttpEntity(params, headers);

        ResponseEntity<JsonNode> responseNode=restTemplate.exchange(TOKEN_URI, HttpMethod.POST,entity,JsonNode.class);
        JsonNode accessTokenNode=responseNode.getBody();
        return accessTokenNode.get("access_token").asText();
    }



}
