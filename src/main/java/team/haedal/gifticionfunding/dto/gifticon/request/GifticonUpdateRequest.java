package team.haedal.gifticionfunding.dto.gifticon.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import team.haedal.gifticionfunding.entity.gifticon.Category;
import team.haedal.gifticionfunding.entity.gifticon.GifticonCreate;
import team.haedal.gifticionfunding.entity.gifticon.GifticonUpdate;

import java.time.LocalDate;

@Getter
public class GifticonUpdateRequest {
    private Long price;

    private String name;

    private String category;

    private Long stock;

    private String imageUrl;

    private String description;

    private String brand;

    private String expirationPeriod;

    public GifticonUpdate toCommand(){
        if(this.category == null){
            return GifticonUpdate.builder()
                    .price(price)
                    .name(name)
                    .stock(stock)
                    .imageUrl(imageUrl)
                    .description(description)
                    .brand(brand)
                    .expirationPeriod(LocalDate.parse(expirationPeriod))
                    .build();
        }
        return GifticonUpdate.builder()
                .price(price)
                .name(name)
                .category(Category.valueOf(category))
                .stock(stock)
                .imageUrl(imageUrl)
                .description(description)
                .brand(brand)
                .expirationPeriod(LocalDate.parse(expirationPeriod))
                .build();
    }
}
