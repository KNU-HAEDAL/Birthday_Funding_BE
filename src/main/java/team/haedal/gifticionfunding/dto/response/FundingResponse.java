package team.haedal.gifticionfunding.dto.response;

import lombok.Builder;
import lombok.Getter;
import team.haedal.gifticionfunding.domain.funding.FundingArticle;
import team.haedal.gifticionfunding.domain.Gifticon;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
public class FundingResponse {
    private Long id;
    private String title;
    private String content;
    private String createAt;
    private String endAt;
    private List<Gifticon> gifticonList;

    @Builder
    private FundingResponse(Long id, String title, String content, LocalDateTime createAt, LocalDate endAt, List<Gifticon> gifticonList){
        this.id = id;
        this.title = title;
        this.content = content;
        this.createAt = createAt.toString();
        this.endAt = endAt.toString();
        this.gifticonList = gifticonList;
    }

    public static FundingResponse from(FundingArticle article){
        return FundingResponse.builder()
                .id(article.getId())
                .title(article.getTitle())
                .content(article.getContent())
                .createAt(article.getCreatedAt())
                .endAt(article.getEndAt())
                .gifticonList(article.getGifticonList())
                .build();
    }

}
