package team.haedal.gifticionfunding.entity.gifticon;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.haedal.gifticionfunding.exception.NotEnoughStockException;

import java.time.LocalDate;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
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
    public static Gifticon createGifticon(Long price, String name, Category category, Long stock, String imageUrl, String description, String brand, LocalDate expirationPeriod) {
        Gifticon gifticon = new Gifticon();
        gifticon.price = price;
        gifticon.name = name;
        gifticon.category = category;
        gifticon.stock = stock;
        gifticon.imageUrl = imageUrl;
        gifticon.description = description;
        gifticon.brand = brand;
        gifticon.expirationPeriod = expirationPeriod;
        return gifticon;
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
    public void updateGifticon(GifticonUpdate gifticonUpdate) {
        this.price = gifticonUpdate.getPrice();
        this.name = gifticonUpdate.getName();
        this.category = gifticonUpdate.getCategory();
        this.stock = gifticonUpdate.getStock();
        this.imageUrl = gifticonUpdate.getImageUrl();
        this.description = gifticonUpdate.getDescription();
        this.brand = gifticonUpdate.getBrand();
        this.expirationPeriod = gifticonUpdate.getExpirationPeriod();
    }
}
