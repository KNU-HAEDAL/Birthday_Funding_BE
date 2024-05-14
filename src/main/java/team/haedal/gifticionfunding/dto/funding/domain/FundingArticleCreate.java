package team.haedal.gifticionfunding.dto.funding.domain;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FundingArticleCreate {

    private final String title;
    private final String content;
    private final int duration;
    private final LocalDateTime startAt;
}
