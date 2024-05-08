package team.haedal.gifticionfunding.domain;

import jakarta.persistence.*;

@Entity
public class Friendship {
    @EmbeddedId
    private FriendKey friendKey;
}
