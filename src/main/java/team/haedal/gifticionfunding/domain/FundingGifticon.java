package team.haedal.gifticionfunding.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@NoArgsConstructor
public class FundingGifticon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne()
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Gifticon gifticon;
    @ManyToOne()
    @OnDelete(action = OnDeleteAction.CASCADE)
    private FundingArticle fundingArticle;

    @Builder
    private FundingGifticon(Long id, Gifticon gifticon, FundingArticle fundingArticle){
        this.id = id;
        this.fundingArticle = fundingArticle;
        this.gifticon = gifticon;
    }

    public static FundingGifticon of(Gifticon gifticon, FundingArticle fundingArticle){
        return FundingGifticon.builder()
                .gifticon(gifticon)
                .fundingArticle(fundingArticle)
                .build();
    }

}
