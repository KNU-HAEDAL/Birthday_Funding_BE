package team.haedal.gifticionfunding.entity.gifticon;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class GifticonCreate {
    private final Long price;
    private final String name;
    private final Category category;
    private final Long stock;
    private final String imageUrl;
    private final String description;
    private final String brand;
    private final LocalDate expirationPeriod;
}
