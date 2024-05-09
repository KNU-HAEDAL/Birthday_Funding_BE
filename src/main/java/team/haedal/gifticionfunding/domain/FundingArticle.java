package team.haedal.gifticionfunding.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
public class FundingArticle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne()
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Member member;
    private LocalDateTime createdAt;
    private LocalDateTime endAt;
    private String title;
    @Column(length = 50000)
    private String content;

    @Builder
    private FundingArticle(Long id, Member member, LocalDateTime createdAt, LocalDateTime endAt, String title, String content){
        this.id = id;
        this.member = member;
        this.createdAt = createdAt;
        this.endAt = endAt;
        this.title = title;
        this.content = content;
    }

//    public static FundingArticle
}
