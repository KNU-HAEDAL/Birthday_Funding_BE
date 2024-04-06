package team.haedal.gifticionfunding.domain;

import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.haedal.gifticionfunding.dto.request.GifticonRequest;

import java.time.LocalDate;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Gifticon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int price;
    private String name;
    private Long stock;
    @Nullable
    private String imageUrl;
    private String description;
    private String brand;
    private LocalDate expirationPeriod;

    public static Gifticon from(GifticonRequest request){
        return Gifticon.builder()
                .price(request.getPrice())
                .name(request.getName())
                .stock(request.getStock())
                .imageUrl(request.getImageUrl())
                .description(request.getDescription())
                .brand(request.getBrand())
                .expirationPeriod(request.getExpirationPeriod())
                .build();
    }
    public Gifticon update(GifticonRequest request) {
        this.price = request.getPrice();
        this.name = request.getName();
        this.stock = request.getStock();
        this.imageUrl = request.getImageUrl();
        this.description = request.getDescription();
        this.brand = request.getBrand();
        this.expirationPeriod = request.getExpirationPeriod();
        return this;
    }
}
