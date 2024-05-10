package team.haedal.gifticionfunding.entity.gifticon;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import team.haedal.gifticionfunding.global.error.NotEnoughStockException;

import java.time.LocalDate;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Slf4j
public class Gifticon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long price;

    @NotNull
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Category category;

    @NotNull
    private Long stock;

    private String imageUrl;

    @NotNull
    private String description;

    @NotNull
    private String brand;

    @NotNull
    private LocalDate expirationPeriod;

    /** 생성 메서드 */
    public static Gifticon createGifticon(GifticonCreate gifticonCreate) {
        return Gifticon.builder()
                .price(gifticonCreate.getPrice())
                .name(gifticonCreate.getName())
                .category(gifticonCreate.getCategory())
                .stock(gifticonCreate.getStock())
                .imageUrl(gifticonCreate.getImageUrl())
                .description(gifticonCreate.getDescription())
                .brand(gifticonCreate.getBrand())
                .expirationPeriod(gifticonCreate.getExpirationPeriod())
                .build();
    }

    /** 빌더 패턴 */
    @Builder
    private Gifticon(Long price, String name, Category category, Long stock, String imageUrl, String description, String brand, LocalDate expirationPeriod) {
        this.price = price;
        this.name = name;
        this.category = category;
        this.stock = stock;
        this.imageUrl = imageUrl;
        this.description = description;
        this.brand = brand;
        this.expirationPeriod = expirationPeriod;
    }

    //==비즈니스 로직==//

    /**
     * 재고 감소
     */
    public void removeStock(Long quantity) {
        Long restStock = this.stock - quantity;
        if (this.stock < 0) {
            throw new NotEnoughStockException("재고가 부족합니다.");
        }
        this.stock = restStock;
    }

    /**
     * 재고 증가
     */
    public void addStock(Long quantity) {
        this.stock += quantity;
    }

    /**
     * 상품 수정
     */
    public Gifticon updateGifticon(final GifticonUpdate gifticonUpdate) {
        this.price = gifticonUpdate.getPrice() != null ? gifticonUpdate.getPrice() : this.price;
        this.name = gifticonUpdate.getName() != null ? gifticonUpdate.getName() : this.name;
        this.category = gifticonUpdate.getCategory() != null ? gifticonUpdate.getCategory() : this.category;
        this.stock = gifticonUpdate.getStock() != null ? gifticonUpdate.getStock() : this.stock;
        this.imageUrl = gifticonUpdate.getImageUrl() != null ? gifticonUpdate.getImageUrl() : this.imageUrl;
        this.description = gifticonUpdate.getDescription() != null ? gifticonUpdate.getDescription() : this.description;
        this.brand = gifticonUpdate.getBrand() != null ? gifticonUpdate.getBrand() : this.brand;
        this.expirationPeriod = gifticonUpdate.getExpirationPeriod() != null ? gifticonUpdate.getExpirationPeriod() : this.expirationPeriod;

        return this;
    }


}
