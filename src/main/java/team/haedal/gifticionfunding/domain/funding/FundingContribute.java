package team.haedal.gifticionfunding.domain.funding;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import team.haedal.gifticionfunding.domain.Member;
import team.haedal.gifticionfunding.domain.MemberGifticon;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class FundingContribute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Member participant;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private FundingArticle fundingArticle;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private MemberGifticon memberGifticon;

    private LocalDateTime createdAt;

    private int point;

    @Builder
    private  FundingContribute(Member participant, FundingArticle fundingArticle, MemberGifticon memberGifticon, LocalDateTime createdAt, int point){
        this.participant = participant;
        this.fundingArticle = fundingArticle;
        this.memberGifticon = memberGifticon;
        this.createdAt = createdAt;
        this.point = point;
    }

    public static FundingContribute of(Member participant, FundingArticle fundingArticle, MemberGifticon memberGifticon, int point){
        return FundingContribute.builder()
                .participant(participant)
                .fundingArticle(fundingArticle)
                .memberGifticon(memberGifticon)
                .createdAt(LocalDateTime.now())
                .point(point)
                .build();
    }


}
