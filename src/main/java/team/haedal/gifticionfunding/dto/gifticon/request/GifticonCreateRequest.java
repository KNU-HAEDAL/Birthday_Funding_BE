package team.haedal.gifticionfunding.dto.gifticon.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import team.haedal.gifticionfunding.entity.gifticon.Category;
import team.haedal.gifticionfunding.entity.gifticon.GifticonCreate;
import team.haedal.gifticionfunding.entity.gifticon.GifticonPurchase;

import java.time.LocalDate;

@Getter
public class GifticonCreateRequest{
    @Min(value = 1,message = "가격은 1원 이상이어야 합니다.")
    private Long price;

    @NotBlank(message = "상품명은 필수 입력 값입니다.")
    private String name;

    @NotBlank(message = "카테고리는 필수 입력 값입니다.")
    private String category;

    @Min(value=1,message = "재고는 1 이상이어야 합니다.")
    private Long stock;

    private String imageUrl;

    @NotBlank(message = "설명은 필수 입력 값입니다.")
    private String description;

    @NotBlank(message = "브랜드는 필수 입력 값입니다.")
    private String brand;

    @NotBlank(message = "유효기간은 필수 입력 값입니다.")
    private String expirationPeriod;

    public GifticonCreate toCommand(){
        return GifticonCreate.builder()
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
