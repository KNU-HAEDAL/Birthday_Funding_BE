package team.haedal.gifticionfunding.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import team.haedal.gifticionfunding.entity.user.FriendRequest;

public interface FriendRequestJpaRepository extends JpaRepository<FriendRequest, Long> {
}
