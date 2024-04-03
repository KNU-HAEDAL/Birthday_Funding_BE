package team.haedal.gifticionfunding.entity.gifticon;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Gifticon {
    @Id
    private Long id;

    @NotNull
    private String price;

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
}
