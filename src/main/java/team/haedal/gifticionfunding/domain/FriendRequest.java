package team.haedal.gifticionfunding.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

@Entity
public class FriendRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne()
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Member sender;
    @ManyToOne()
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Member receiver;
    private LocalDateTime createdAt;
}
