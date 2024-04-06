package team.haedal.gifticionfunding.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class GifticonDetailResponse {
    private Long id;
    private String name;
    private String brand;
    private String imageUrl;
    private LocalDate dateOfUse;
    private LocalDate expirationDate;
}
