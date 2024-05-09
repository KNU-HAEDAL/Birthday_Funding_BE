package team.haedal.gifticionfunding.dto.response;

import lombok.Builder;
import team.haedal.gifticionfunding.domain.FundingArticle;
import team.haedal.gifticionfunding.domain.Gifticon;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class FundingResponse {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createAt;
    private LocalDate endAt;
    private List<Gifticon> gifticonList;

    @Builder
    private FundingResponse(Long id, String title, String content, LocalDateTime createAt, LocalDate endAt, List<Gifticon> gifticonList){
        this.id = id;
        this.title = title;
        this.content = content;
        this.createAt = createAt;
        this.endAt = endAt;
        this.gifticonList = gifticonList;
    }

    public static FundingResponse of(FundingArticle article, List<Gifticon> gifticonList){
        return FundingResponse.builder()
                .id(article.getId())
                .title(article.getTitle())
                .content(article.getContent())
                .createAt(article.getCreatedAt())
                .endAt(article.getEndAt())
                .gifticonList(gifticonList)
                .build();
    }

}
