package team.haedal.gifticionfunding.repository.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import team.haedal.gifticionfunding.entity.user.Friendship;

public interface FriendshipJpaRepository extends JpaRepository<Friendship, Long> {
    Page<Friendship> findAllByUser1_Id(Long userId, Pageable pageable);
}
