package team.haedal.gifticionfunding.entity.user;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import team.haedal.gifticionfunding.entity.common.BaseTimeEntity;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Friendship extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_1_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user1;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_2_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user2;

    @Builder
    private Friendship(User user1, User user2) {
        this.user1 = user1;
        this.user2 = user2;
    }

    public static Friendship create(User user1, User user2) {
        // user1이 user2보다 id가 클 경우 user1과 user2를 바꿔준다.
        // db에는 user1의 id가 더 작은 값이 들어가게 된다.
        if(user1.getId() > user2.getId())
            return Friendship.builder()
                    .user1(user2)
                    .user2(user1)
                    .build();
        return Friendship.builder()
                .user1(user1)
                .user2(user2)
                .build();
    }

}
