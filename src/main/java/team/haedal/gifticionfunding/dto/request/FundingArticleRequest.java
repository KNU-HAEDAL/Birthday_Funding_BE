package team.haedal.gifticionfunding.dto.request;

import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
public class FundingArticleRequest {
    /**
     * - 사용자는 펀딩을 생성할 수 있다.
     * - 펀딩 게시글 생성시 제목, 내용이 포함된다.
     * - 펀딩 시 2가지 금액대의 물건을 등록할 수 있다.
     *     - 2가지 금액대가 아니라 여러개 등록 가능하게
     * - 생일 14일전~ 당일 까지 펀딩 기간을 설정할 수 있다.
     */
    private String title;
    private String content;
    private LocalDate endAt;
    private List<Long> gifticonIdList;
}
