package team.haedal.gifticionfunding.dto.funding.request;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import team.haedal.gifticionfunding.dto.funding.domain.FundingArticleCreate;


@Builder
public record FundingCreateRequest(
    @NotBlank(message = "제목은 필수 입력 값입니다.")
    String title,
    @NotBlank(message = "내용은 필수 입력 값입니다.")
    String content,
    @NotNull(message = "펀딩 기간은 필수 입력 값입니다.")
    @Min(value = 1, message = "펀딩 기간은 1일 이상이어야 합니다.")
    int duration,
    @NotNull(message = "펀딩 시작일은 필수 입력 값입니다.")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime startAt,
    @NotNull(message = "기프티콘 리스트는 필수 입력 값입니다.")
    List<Long> gifticonIds
) {

    public FundingArticleCreate toDomain() {
        return FundingArticleCreate.builder()
            .title(title)
            .content(content)
            .duration(duration)
            .startAt(startAt)
            .build();
    }

}
