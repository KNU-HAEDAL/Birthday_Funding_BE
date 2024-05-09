package team.haedal.gifticionfunding.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import team.haedal.gifticionfunding.domain.enums.FriendStatus;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Friendship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Member friend;

    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private FriendStatus status;

    @Builder
    private Friendship(Long id, Member member, Member friend, LocalDateTime createdAt, FriendStatus status) {
        this.id = id;
        this.member = member;
        this.friend = friend;
        this.createdAt = createdAt;
        this.status = status;
    }

    public static Friendship request(Member member, Member friend) {
        return Friendship.builder()
                .member(member)
                .friend(friend)
                .createdAt(LocalDateTime.now())
                .status(FriendStatus.WAITING)
                .build();
    }

    public static Friendship finish(Member member, Member friend) {
        return Friendship.builder()
                .member(member)
                .friend(friend)
                .createdAt(LocalDateTime.now())
                .status(FriendStatus.ACCEPT)
                .build();
    }

    public Friendship accept(){
        this.status = FriendStatus.ACCEPT;
        return this;
    }


}
