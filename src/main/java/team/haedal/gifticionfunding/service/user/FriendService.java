package team.haedal.gifticionfunding.service.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.haedal.gifticionfunding.dto.common.PagingRequest;
import team.haedal.gifticionfunding.dto.common.PagingResponse;
import team.haedal.gifticionfunding.dto.user.response.FriendInfoDto;
import team.haedal.gifticionfunding.dto.user.response.FriendRequestReceiverDto;
import team.haedal.gifticionfunding.dto.user.response.FriendRequestSenderDto;
import team.haedal.gifticionfunding.entity.user.FriendRequest;
import team.haedal.gifticionfunding.entity.user.Friendship;
import team.haedal.gifticionfunding.entity.user.User;
import team.haedal.gifticionfunding.global.error.NotFoundFriendRequestException;
import team.haedal.gifticionfunding.global.error.NotFoundUserException;
import team.haedal.gifticionfunding.repository.user.FriendRequestJpaRepository;
import team.haedal.gifticionfunding.repository.user.FriendshipJpaRepository;
import team.haedal.gifticionfunding.repository.user.UserJpaRepository;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class FriendService {

    private final UserJpaRepository userJpaRepository;
    private final FriendRequestJpaRepository friendRequestJpaRepository;
    private final FriendshipJpaRepository friendshipJpaRepository;

    /**
     * 친구 추가 요청
     *
     * @return FriendRequest id
     */
    public Long requestFriend(Long userId, Long friendId) {
        User requester = userJpaRepository.findById(userId)
            .orElseThrow(() -> new NotFoundUserException("해당 유저가 존재하지 않습니다."));
        User requestee = userJpaRepository.findById(friendId)
            .orElseThrow(() -> new NotFoundUserException("해당 유저가 존재하지 않습니다."));

        /**
         * 이미 친구인지 확인
         * user1과 user2가 친구인지 확인
         * user2와 user1이 친구인지 확인
         */
        if (friendshipJpaRepository.existsByUser1AndUser2(requester, requestee) ||
            friendshipJpaRepository.existsByUser1AndUser2(requestee, requester)) {
            throw new IllegalArgumentException("이미 친구인 상대방에게는 친구 초대 요청을 보낼 수 없습니다.");
        }

        FriendRequest friendRequest = FriendRequest.create(requester, requestee);
        return friendRequestJpaRepository.save(friendRequest).getId();
    }

    /**
     * 친구 추가 요청 보낸 목록 조회
     */
    @Transactional(readOnly = true)
    public PagingResponse<FriendRequestReceiverDto> getFriendRequestSenderPaging(
        PagingRequest pagingRequest, Long requesterUserId) {
        Page<FriendRequest> friendRequestPage = friendRequestJpaRepository.findAllByRequesterId(
            requesterUserId, pagingRequest.toPageable());
        return PagingResponse.from(friendRequestPage, FriendRequestReceiverDto::from);
    }

    /**
     * 친구 추가 요청 받은 목록 조회
     */
    @Transactional(readOnly = true)
    public PagingResponse<FriendRequestSenderDto> getFriendRequestReceiverPaging(
        PagingRequest pagingRequest, Long requesteeUserId) {
        Page<FriendRequest> friendRequestPage = friendRequestJpaRepository.findAllByRequesteeId(
            requesteeUserId, pagingRequest.toPageable());
        return PagingResponse.from(friendRequestPage, FriendRequestSenderDto::from);
    }

    /**
     * 친구 추가 요청 수락
     */
    public void acceptFriendRequest(Long friendRequestId) {
        log.info(friendRequestId.toString());
        FriendRequest friendRequest = friendRequestJpaRepository.findById(friendRequestId)
            .orElseThrow(() -> new NotFoundFriendRequestException("해당 친구 요청이 존재하지 않습니다."));
        Pair<Friendship, Friendship> friendShips = friendRequest.makeFriendship();
        friendshipJpaRepository.save(friendShips.getFirst());
        friendshipJpaRepository.save(friendShips.getSecond());
        friendRequestJpaRepository.delete(friendRequest);
    }

    /**
     * 친구 추가 요청 거절
     */
    public void rejectFriendRequest(Long friendRequestId) {
        FriendRequest friendRequest = friendRequestJpaRepository.findById(friendRequestId)
            .orElseThrow(() -> new NotFoundFriendRequestException("해당 친구 요청이 존재하지 않습니다."));
        friendRequestJpaRepository.delete(friendRequest);
    }

    /**
     * 친구 목록 페이징 조회
     */
    @Transactional(readOnly = true)
    public PagingResponse<FriendInfoDto> getFriendPaging(PagingRequest pagingRequest, Long userId) {
        Page<Friendship> friendshipPage = friendshipJpaRepository.findAllByUser1_Id(userId,
            pagingRequest.toPageable());
        return PagingResponse.from(friendshipPage, friendship -> FriendInfoDto.from(friendship));
    }

    /**
     * 친구 삭제
     */
    public void deleteFriend(Long user1Id, Long user2Id) {
        /**
         * Pair를 사용하여 (user1과 user2), ( user2와 user1)가 친구인지 확인
         * A->B, B->A 친구 관계를 삭제 // A->B 는 exception 발생, B->A는 exception 발생하지 않음
         */
        Pair<Object, Object> friendships = friendshipJpaRepository
            .findByUser1_IdAndUser2_Id(user1Id, user2Id)
            .map(friendship -> Pair.of(friendship, friendshipJpaRepository
                .findByUser1_IdAndUser2_Id(user2Id, user1Id).orElse(null)))
            .orElseThrow(() -> new IllegalArgumentException("해당 친구가 존재하지 않습니다."));
        friendshipJpaRepository.delete((Friendship) friendships.getFirst());
        friendshipJpaRepository.delete((Friendship) friendships.getSecond());
    }

    public PagingResponse<FriendInfoDto> getRelatedFriendPaging(PagingRequest pagingRequest,
        Long userId) {
        Page<Friendship> friendshipPage = friendshipJpaRepository.getFriendshipRelatedByUser1_id(
            userId, pagingRequest.toPageable());
        return PagingResponse.from(friendshipPage, friendship -> FriendInfoDto.from(friendship));
    }
}
