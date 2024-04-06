package team.haedal.gifticionfunding.dto.request;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class GifticonRequest {
    private int price;
    private String name;
    private Long stock;
    private String imageUrl;
    private String description;
    private String brand;
    private LocalDate expirationPeriod;
}
