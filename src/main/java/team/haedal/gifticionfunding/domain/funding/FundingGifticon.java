package team.haedal.gifticionfunding.domain.funding;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import team.haedal.gifticionfunding.domain.Gifticon;


@Entity
@NoArgsConstructor
public class FundingGifticon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne()
    @JoinColumn(name = "gifticon_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Gifticon gifticon;
    @ManyToOne()
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "funding_article_id")
    private FundingArticle fundingArticle;
    private int point;

    @Builder
    private FundingGifticon(FundingArticle fundingArticle, Gifticon gifticon) {
        this.fundingArticle = fundingArticle;
        this.gifticon = gifticon;
        this.point = gifticon.getPrice();
    }

    public static FundingGifticon create(FundingArticle fundingArticle, Gifticon gifticon) {
        return FundingGifticon.builder()
                .fundingArticle(fundingArticle)
                .gifticon(gifticon)
                .build();
    }
}
