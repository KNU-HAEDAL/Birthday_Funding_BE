package team.haedal.gifticionfunding.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.haedal.gifticionfunding.domain.Gifticon;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class GifticonResponse {
    private Long id;
    private String name;
    private int price;
    private String imageUrl;

    public static GifticonResponse from(Gifticon gifticon){
        return GifticonResponse.builder()
                .id(gifticon.getId())
                .name(gifticon.getName())
                .price(gifticon.getPrice())
                .imageUrl(gifticon.getImageUrl())
                .build();
    }
}
