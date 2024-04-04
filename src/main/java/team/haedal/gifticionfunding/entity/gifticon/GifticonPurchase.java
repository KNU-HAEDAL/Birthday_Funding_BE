package team.haedal.gifticionfunding.entity.gifticon;

import lombok.Builder;
import lombok.Getter;
import team.haedal.gifticionfunding.entity.user.User;

import java.time.LocalDate;

@Builder
@Getter
public class GifticonPurchase {
    private final User buyer;
    private final User owner;
    private final Gifticon gifticon;
    private final LocalDate purchaseDate;
    private final LocalDate expirationDate;
}
