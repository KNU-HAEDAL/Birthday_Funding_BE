package team.haedal.gifticionfunding.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import team.haedal.gifticionfunding.domain.Friendship;
import team.haedal.gifticionfunding.domain.Member;
import team.haedal.gifticionfunding.domain.enums.FriendStatus;
import team.haedal.gifticionfunding.repository.vo.FriendOnly;
import team.haedal.gifticionfunding.repository.vo.MemberOnly;

import java.util.Optional;

public interface FriendRepository extends JpaRepository<Friendship, Long> {
    Page<FriendOnly> findAllByMemberAndStatus(Pageable pageable, Member member, FriendStatus status);
    Page<MemberOnly> findAllByFriendAndStatus(Pageable pageable, Member friend, FriendStatus status);

    @Query(value = "select * from Friendship where member_id = :memberId and friend_id = :friendId", nativeQuery = true)
    Optional<Friendship> findByMemberAndFriend(@Param("memberId") Long memberId, @Param("friendId") Long friendId);

    Optional<Friendship> findByMemberAndFriend(Member member, Member friend);

}
