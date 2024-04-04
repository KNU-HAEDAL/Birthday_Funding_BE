package team.haedal.gifticionfunding.entity.gifticon;

import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.haedal.gifticionfunding.entity.user.User;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserGifticon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buyer_id")
    private User buyer;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "owner_id")
    private User owner;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "gifticon_id")
    private Gifticon gifticon;

    @NotNull
    private LocalDate purchaseDate;

    @NotNull
    private LocalDate expirationDate;


    private LocalDate usedDate;

    @Column(length = 12)
    private String giftCode;

    //--비즈니스 로직--//
    /** 기프티콘 구매 */
    public static UserGifticon purchaseGifticon(GifticonPurchase gifticonPurchase){
        UserGifticon userGifticon = new UserGifticon();
        userGifticon.buyer = gifticonPurchase.getBuyer();
        userGifticon.owner = gifticonPurchase.getOwner();
        userGifticon.gifticon = gifticonPurchase.getGifticon();
        userGifticon.purchaseDate = gifticonPurchase.getPurchaseDate();
        userGifticon.expirationDate = gifticonPurchase.getExpirationDate();
        // 구매한 기프티콘의 재고를 감소시킨다.
        gifticonPurchase.getGifticon().removeStock(gifticonPurchase.getGifticon().getId());
        // 구매한 기프티콘의 금액만큼 구매자의 포인트를 차감한다.
        gifticonPurchase.getBuyer().usePoint(gifticonPurchase.getGifticon().getPrice());
        return userGifticon;
    }

    /** 기프티콘 사용 */
    public String useGifticon(){
        if(this.usedDate != null || this.giftCode != null){
            throw new IllegalStateException("이미 사용한 기프티콘입니다.");
        }
        if(LocalDate.now().isAfter(this.expirationDate)){
            throw new IllegalStateException("기프티콘 유효기간이 만료되었습니다.");
        }
        this.usedDate = LocalDate.now();
        this.giftCode = UUID.randomUUID().toString().substring(0, 12);
        return giftCode;
    }

    /** 기프티콘 소유자 변경 */
    public void changeOwner(User owner){
        if(this.usedDate != null || this.giftCode != null){
            throw new IllegalStateException("이미 사용한 기프티콘은 소유자를 변경할 수 없습니다.");
        }
        if(LocalDate.now().isAfter(this.expirationDate)){
            throw new IllegalStateException("기프티콘 유효기간이 만료되어 소유자를 변경할 수 없습니다.");
        }
        if(this.owner.equals(owner)){
            throw new IllegalStateException("이미 소유한 기프티콘입니다.");
        }

        this.owner = owner;
    }


    //환불 관련해서 status 필요할듯
    /** 기프티콘 환불 */
    public void refundGifticon(){
        if(this.usedDate != null || this.giftCode != null){
            throw new IllegalStateException("이미 사용한 기프티콘은 환불할 수 없습니다.");
        }
        if(LocalDate.now().isAfter(this.expirationDate)){
            throw new IllegalStateException("기프티콘 유효기간이 만료되어 환불할 수 없습니다.");
        }
        // 환불한 기프티콘의 재고를 증가시킨다.
        this.gifticon.addStock(this.gifticon.getId());
        // 소유자에게 기프티콘 금액을 환불한다.
        this.owner.chargePoint(this.gifticon.getPrice());
    }
}
