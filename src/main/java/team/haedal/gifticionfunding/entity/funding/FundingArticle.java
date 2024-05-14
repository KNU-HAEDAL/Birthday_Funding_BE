package team.haedal.gifticionfunding.entity.funding;

import static lombok.AccessLevel.PROTECTED;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.haedal.gifticionfunding.dto.funding.domain.FundingArticleCreate;
import team.haedal.gifticionfunding.entity.user.User;

@Getter
@NoArgsConstructor(access = PROTECTED)
@Entity
public class FundingArticle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    private LocalDateTime createdAt;

    @NotNull
    private LocalDateTime endAt;

    @NotNull
    private String title;

    @NotNull
    private String content;

    @Builder
    private FundingArticle(User user, LocalDateTime createdAt, LocalDateTime endAt, String title,
        String content) {
        this.user = user;
        this.createdAt = createdAt;
        this.endAt = endAt;
        this.title = title;
        this.content = content;
    }

    public static FundingArticle create(FundingArticleCreate fundingArticleCreate, User user) {
        return FundingArticle.builder()
            .user(user)
            .createdAt(LocalDateTime.now())
            .endAt(LocalDateTime.now().plusDays(fundingArticleCreate.getDuration()))
            .title(fundingArticleCreate.getTitle())
            .content(fundingArticleCreate.getContent())
            .build();
    }


}
