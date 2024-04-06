package team.haedal.gifticionfunding.domain;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Entity
public class MemberGifticon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="buyer_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Member buyer;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="owner_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Member owner;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="gifticon_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Gifticon gifticon;
    private LocalDate purchaseDate;
    private LocalDate expirationDate;
    @Nullable
    private LocalDate usedDate;
    @Nullable
    private String giftCode;

}
