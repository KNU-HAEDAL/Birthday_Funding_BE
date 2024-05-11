package team.haedal.gifticionfunding.repository.user;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import team.haedal.gifticionfunding.entity.user.Friendship;
import team.haedal.gifticionfunding.entity.user.User;

public interface FriendshipJpaRepository extends JpaRepository<Friendship, Long> {

    Page<Friendship> findAllByUser1_Id(Long userId, Pageable pageable);

    boolean existsByUser1AndUser2(User requester, User requestee);

    Optional<Object> findByUser1_IdAndUser2_Id(Long user1Id, Long user2Id);

    /**
     * 1. 유저의 친구 조회 2. 그 친구의 친구 조회
     *
     * @param userId
     * @param pageable
     * @return
     */
    @Query(value = "SELECT DISTINCT user_id " +
        "FROM (" +
        "    SELECT user2_id AS user_id FROM friendship WHERE user1_id = :userId " +
        "    UNION " +
        "    SELECT user1_id AS user_id FROM friendship WHERE user2_id = :userId " +
        "    UNION " +
        "    SELECT f2.user2_id AS user_id FROM friendship f1 " +
        "    JOIN friendship f2 ON f1.user2_id = f2.user1_id WHERE f1.user1_id = :userId " +
        "    UNION " +
        "    SELECT f2.user1_id AS user_id FROM friendship f1 " +
        "    JOIN friendship f2 ON f1.user2_id = f2.user1_id WHERE f1.user1_id = :userId" +
        ") AS users " +
        "WHERE user_id <> :userId", nativeQuery = true)
    Page<Friendship> getFriendshipRelatedByUser1_id(Long userId, Pageable pageable);
}
