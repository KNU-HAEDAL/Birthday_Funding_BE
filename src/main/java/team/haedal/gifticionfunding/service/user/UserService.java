package team.haedal.gifticionfunding.service.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import team.haedal.gifticionfunding.dto.user.request.UserCreate;
import team.haedal.gifticionfunding.entity.user.User;
import team.haedal.gifticionfunding.repository.user.UserJpaRepository;

import java.util.Optional;


@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserJpaRepository userJpaRepository;
    private final RestTemplate restTemplate = new RestTemplate();

    @Transactional
    public Optional<User> SignIn(UserCreate userInfo) {
        Optional<User> user = userJpaRepository.findByEmail(userInfo.getEmail());
        if (!user.isPresent()) {
            userJpaRepository.save(User.createUser(userInfo));
            return userJpaRepository.findByEmail(userInfo.getEmail());
        }
        return user;
    }


}
