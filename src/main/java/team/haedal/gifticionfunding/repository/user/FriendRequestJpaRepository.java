package team.haedal.gifticionfunding.repository.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import team.haedal.gifticionfunding.entity.user.FriendRequest;

public interface FriendRequestJpaRepository extends JpaRepository<FriendRequest, Long> {
    Page<FriendRequest> findAllByRequesterId(Long requesteeId, Pageable pageable);
    Page<FriendRequest> findAllByRequesteeId(Long requesterId, Pageable pageable);
}
