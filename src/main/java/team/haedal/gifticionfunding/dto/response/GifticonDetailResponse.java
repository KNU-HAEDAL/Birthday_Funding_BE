package team.haedal.gifticionfunding.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.haedal.gifticionfunding.domain.Gifticon;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class GifticonDetailResponse {
    private Long id;
    private String name;
    private String brand;
    private String imageUrl;
    private LocalDate expirationDate;

    public static GifticonDetailResponse from(Gifticon gifticon){
        return GifticonDetailResponse.builder()
                .id(gifticon.getId())
                .name(gifticon.getName())
                .brand(gifticon.getBrand())
                .imageUrl(gifticon.getImageUrl())
                .expirationDate(gifticon.getExpirationPeriod())
                .build();
    }

}
