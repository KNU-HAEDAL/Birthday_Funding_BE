package team.haedal.gifticionfunding.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import team.haedal.gifticionfunding.domain.Member;
import team.haedal.gifticionfunding.repository.MemberRepository;

import java.util.Map;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class Oauth2UserService extends DefaultOAuth2UserService {
    private final MemberRepository memberRepository;


    @Override
    public OAuth2User loadUser(OAuth2UserRequest request) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(request);
        System.out.println(oAuth2User.getAttributes().toString());
        Map<String,String > properties = (Map<String, String>) oAuth2User.getAttributes().get("properties");
        saveMember(OAuthAttributes.of(properties));
        return oAuth2User;
    }

    private void saveMember(OAuthAttributes attributes){
        Optional<Member> member = memberRepository.findByNickname(attributes.getNickname());
        if(member.isEmpty()){
            memberRepository.save(attributes.toMember());
        }
    }
}
