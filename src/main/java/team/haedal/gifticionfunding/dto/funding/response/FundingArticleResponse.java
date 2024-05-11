package team.haedal.gifticionfunding.dto.funding.response;

import java.time.LocalDateTime;
import lombok.Builder;
import team.haedal.gifticionfunding.entity.funding.FundingArticle;

@Builder
public record FundingArticleResponse(
    Long id,
    LocalDateTime startAt,
    LocalDateTime endAt,
    String title,
    String content

) {

    public static FundingArticleResponse from(FundingArticle fundingArticle) {
        return FundingArticleResponse.builder()
            .id(fundingArticle.getId())
            .startAt(fundingArticle.getCreatedAt())
            .endAt(fundingArticle.getEndAt())
            .title(fundingArticle.getTitle())
            .content(fundingArticle.getContent())
            .build();
    }


}
