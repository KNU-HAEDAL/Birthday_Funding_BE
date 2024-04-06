package team.haedal.gifticionfunding.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class GiftiConResponse {
    private Long id;
    private String name;
    private int price;
    private String imageUrl;
}
