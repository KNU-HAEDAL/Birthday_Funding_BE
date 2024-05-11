package team.haedal.gifticionfunding.service.friend;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.time.LocalDate;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageRequest;
import team.haedal.gifticionfunding.dto.common.PagingRequest;
import team.haedal.gifticionfunding.dto.common.PagingResponse;
import team.haedal.gifticionfunding.dto.user.request.UserCreate;
import team.haedal.gifticionfunding.dto.user.response.FriendRequestReceiverDto;
import team.haedal.gifticionfunding.entity.user.Friendship;
import team.haedal.gifticionfunding.entity.user.Role;
import team.haedal.gifticionfunding.entity.user.User;
import team.haedal.gifticionfunding.global.error.NotFoundUserException;
import team.haedal.gifticionfunding.repository.user.FriendshipJpaRepository;
import team.haedal.gifticionfunding.repository.user.UserJpaRepository;
import team.haedal.gifticionfunding.service.user.FriendService;

@DataJpaTest
@DisplayName("FriendService 테스트")
@Import({FriendService.class})
public class friendServiceTest {

    @Autowired
    private FriendService friendService;

    @Autowired
    private FriendshipJpaRepository friendshipJpaRepository;
    @Autowired
    private UserJpaRepository userJpaRepository;

    @AfterEach
    void tearDown() {
        userJpaRepository.deleteAllInBatch();
        friendshipJpaRepository.deleteAllInBatch();
    }

    @Test
    @DisplayName("존재하지 않은 유저에게 친구 추가 요청시 NotFoundUserException 발생")
    void requestFriend_NotFoundFriendRequestException() {
        // given
        /**
         * 유저 생성
         */
        Long userId = 1L;
        Long friendId = 2L;

        userJpaRepository.save(User.createUser(UserCreate.builder()
            .email("test@123").
            nickname("test").
            point(0L).
            birthdate(LocalDate.parse("1999-01-01")).
            profileImageUrl("test.com").
            role(Role.USER).
            build()));

        // when, then
        assertThatCode(() -> friendService.requestFriend(userId, friendId))
            .isInstanceOf(NotFoundUserException.class)
            .hasMessage("해당 유저가 존재하지 않습니다.");

    }

    @Test
    @DisplayName("이미 친구인 상대방에게 친구 추가 요청시 IllegalArgumentException 발생")
    void requestFriend_IllegalArgumentException() {
        // given
        /**
         * 유저1, 유저2 생성
         */
        User user1 = userJpaRepository.save(User.createUser(UserCreate.builder()
            .email("test@123").
            nickname("test").
            point(0L).
            birthdate(LocalDate.parse("1999-01-01")).
            profileImageUrl("test.com").
            role(Role.USER).
            build()));

        User user2 = userJpaRepository.save(User.createUser(UserCreate.builder()
            .email("test2@123").
            nickname("test2").
            point(0L).
            birthdate(LocalDate.parse("1999-01-01")).
            profileImageUrl("test.com").
            role(Role.USER).
            build()));

        /**
         * 친구 관계 생성
         */
        Friendship friendship = Friendship.create(user1, user2);
        friendshipJpaRepository.save(friendship);

        // when, then
        assertThatCode(() -> friendService.requestFriend(user1.getId(), user2.getId()))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("이미 친구인 상대방에게는 친구 초대 요청을 보낼 수 없습니다.");
    }

    @Test
    @DisplayName("친구 추가 요청 보낸 목록 페이징 조회")
    void getFriendRequestSenderPaging() {
        // given
        /**
         * 유저1, 유저2, 유저3 생성
         */
        User user1 = userJpaRepository.save(User.createUser(UserCreate.builder()
            .email("test@123").
            nickname("test").
            point(0L).
            birthdate(LocalDate.parse("1999-01-01")).
            profileImageUrl("test.com").
            role(Role.USER).
            build()));

        User user2 = userJpaRepository.save(User.createUser(UserCreate.builder()
            .email("test2@123").
            nickname("test2").
            point(0L).
            birthdate(LocalDate.parse("1999-01-01")).
            profileImageUrl("test.com").
            role(Role.USER).
            build()));

        User user3 = userJpaRepository.save(User.createUser(UserCreate.builder()
            .email("test3@123").
            nickname("test3").
            point(0L).
            birthdate(LocalDate.parse("1999-01-01")).
            profileImageUrl("test.com").
            role(Role.USER).
            build()));

        // 친구 요청 생성
        friendService.requestFriend(user1.getId(), user2.getId());
        friendService.requestFriend(user1.getId(), user3.getId());

        PageRequest pageRequest = PageRequest.of(0, 10);
        PagingRequest pagingRequest = PagingRequest.builder()
            .page(0)
            .size(10)
            .build();

        // when
        PagingResponse<FriendRequestReceiverDto> friendRequestLists = friendService.getFriendRequestSenderPaging(
            pagingRequest, user1.getId());

        //then
        assertThat(friendRequestLists.data().size()).isEqualTo(2);
    }


}
