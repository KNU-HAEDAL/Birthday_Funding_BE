package team.haedal.gifticionfunding.entity.funding;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.haedal.gifticionfunding.entity.gifticon.Gifticon;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FundingArticleGifticon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private FundingArticle fundingArticle;

    @ManyToOne(fetch = FetchType.LAZY)
    private Gifticon gifticon;

    @Builder
    private FundingArticleGifticon(FundingArticle fundingArticle, Gifticon gifticon) {
        this.fundingArticle = fundingArticle;
        this.gifticon = gifticon;
    }

    public static FundingArticleGifticon create(FundingArticle fundingArticle, Gifticon gifticon) {
        return FundingArticleGifticon.builder()
            .fundingArticle(fundingArticle)
            .gifticon(gifticon)
            .build();
    }
}
