package team.haedal.gifticionfunding.domain;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class FriendKey implements Serializable {
    private Long member;
    private Long friend;
}
