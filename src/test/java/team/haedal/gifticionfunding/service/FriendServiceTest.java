package team.haedal.gifticionfunding.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import team.haedal.gifticionfunding.domain.Friendship;
import team.haedal.gifticionfunding.domain.Member;
import team.haedal.gifticionfunding.domain.enums.FriendStatus;
import team.haedal.gifticionfunding.domain.enums.Role;
import team.haedal.gifticionfunding.repository.FriendRepository;
import team.haedal.gifticionfunding.repository.MemberRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;


import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
public class FriendServiceTest {
    @Mock
    private MemberRepository memberRepository;
    @Mock
    private FriendRepository friendRepository;
    @InjectMocks
    private FriendService friendService;

    @Override
    public boolean equals(Object o){
        Friendship friendship = (Friendship) o;
        return true;
    }



    //TODO: Test: 승인된 친구만 조회되는지(승인된 친구가 요청목록에 없어지는지), 친구 중복 요청안되는지
    //TODO: Test: 만약에 두명이 서로 친구 요청을 보내고 하나의 요청이 승인되면 -> 둘은 친구임 -> 근데 waiting인게 디비에 영원히 남아있음
    //TODO: Test: 친구가 서로 동시에 요청을 보낸다면?

    @Test
    void requestFriend() {
        String[] attrs = toArray("좋은","빠른");
    }

    static <T> T[] pickTwo(T a, T b) {
        System.out.println("pickTwo: "+a.getClass());
        return toArray(a, b);
    }
    static <T> T[] toArray(T... args){
        System.out.println("toArray: "+args.getClass());
        return args;
    }



    @Test
    void getRequestedFriends() {
        //given
        Member member = createMember("사용자");
        Member friend1 = createMember("친구");
        Mockito.when(memberRepository.getUserByName(member.getNickname())).thenReturn(member);
        Mockito.when(memberRepository.findById(friend1.getId())).thenReturn(Optional.of(friend1));

        //given
        Friendship friendship = createFriendRequest(friend1,member);

        friendService.requestFriend(member.getNickname(),friend1.getId());

        verify(friendRepository).save(any(Friendship.class));
    }

    @Test
    void acceptFriend() {
    }

    @Test
    @DisplayName("사용자와 친구관계인 member를 조회한다.")
    void getFriends() {
        //given




        //when

    }


    @Test
    void rejectFriend() {
    }


    Member createMember(String name){
        return Member.builder()
                .email("email@email.com")
                .nickname(name)
                .point(0L)
                .birthdate(LocalDate.of(2001,9,15))
                .profileImageUrl("http://profile.png")
                .role(Role.MEMBER)
                .build();
    }

    Friendship createFriendRequest(Member to, Member from){
        return Friendship.builder()
                .createdAt(LocalDateTime.now())
                .member(from)
                .friend(to)
                .status(FriendStatus.WAITING)
                .build();
    }




}
