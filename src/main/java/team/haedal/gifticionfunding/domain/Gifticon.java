package team.haedal.gifticionfunding.domain;

import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
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
}
