package team.haedal.gifticionfunding.dto.gifticon.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;
import team.haedal.gifticionfunding.entity.gifticon.Category;
import team.haedal.gifticionfunding.entity.gifticon.Gifticon;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GifticonResponse {
    private Long id;
    private Long price;
    private String name;
    private Category category;
    private Long stock;
    private String imageUrl;
    private String description;
    private String brand;
    private LocalDate expirationPeriod;


    @Builder
    private GifticonResponse(final Long id, final Long price, final String name, final Category category, final Long stock, final String imageUrl, final String description, final String brand, final LocalDate expirationPeriod) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.category = category;
        this.stock = stock;
        this.imageUrl = imageUrl;
        this.description = description;
        this.brand = brand;
        this.expirationPeriod = expirationPeriod;
    }

    public static GifticonResponse from(final Gifticon gifticon){
        return GifticonResponse.builder()
                .id(gifticon.getId())
                .price(gifticon.getPrice())
                .name(gifticon.getName())
                .category(gifticon.getCategory())
                .stock(gifticon.getStock())
                .imageUrl(gifticon.getImageUrl())
                .description(gifticon.getDescription())
                .brand(gifticon.getBrand())
                .expirationPeriod(gifticon.getExpirationPeriod())
                .build();
    }

    public static List<GifticonResponse> fromList(final List<Gifticon> gifticons){
        return gifticons.stream()
                .map(GifticonResponse::from)
                .collect(Collectors.toUnmodifiableList());
    }
}
