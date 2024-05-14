package team.haedal.gifticionfunding.entity.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.util.Pair;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FriendRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User requester;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User requestee;

    @NotNull
    private LocalDateTime createdAt;

    @Builder
    private FriendRequest(User requester, User requestee, LocalDateTime createdAt) {
        this.requester = requester;
        this.requestee = requestee;
        this.createdAt = createdAt;
    }

    public static FriendRequest create(User requester, User requestee) {
        return FriendRequest.builder()
                .requester(requester)
                .requestee(requestee)
                .createdAt(LocalDateTime.now())
                .build();
    }

    public Pair<Friendship, Friendship> makeFriendship() {
        Friendship friendship1 = Friendship.create(requester, requestee);
        Friendship friendship2 = Friendship.create(requestee, requester);
        return Pair.of(friendship1, friendship2);
    }

}
