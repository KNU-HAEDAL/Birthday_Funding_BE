package team.haedal.gifticionfunding.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.haedal.gifticionfunding.domain.Friendship;
import team.haedal.gifticionfunding.domain.Member;
import team.haedal.gifticionfunding.domain.enums.FriendStatus;
import team.haedal.gifticionfunding.dto.PageResponse;
import team.haedal.gifticionfunding.dto.response.friend.FriendResponse;
import team.haedal.gifticionfunding.exception.CustomException;
import team.haedal.gifticionfunding.exception.ErrorCode;
import team.haedal.gifticionfunding.repository.vo.FriendOnly;
import team.haedal.gifticionfunding.repository.FriendRepository;
import team.haedal.gifticionfunding.repository.vo.MemberOnly;
import team.haedal.gifticionfunding.repository.MemberRepository;

import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class FriendService {
    private final MemberRepository memberRepository;
    private final FriendRepository friendRepository;


    @Transactional(readOnly = true)
    public PageResponse<FriendResponse> getFriends(int page, String name){
        Member member = memberRepository.getUserByEmail(name);
        Page<FriendOnly> pageData = friendRepository.findAllByMemberAndStatus(PageRequest.of(page, 10), member, FriendStatus.ACCEPT);

        return PageResponse.<FriendResponse>builder()
                .content(pageData.getContent().stream()
                        .map(data -> FriendResponse.from(data.getFriend()))
                        .collect(Collectors.toList()))
                .page(pageData.getNumber())
                .isLast(pageData.isLast())
                .build();
    }


    @Transactional()
    public void requestFriend(String name, Long friendId){
        Member sender = memberRepository.getUserByEmail(name);
        Member receiver = memberRepository.getUserById(friendId);

        friendRepository.save(Friendship.request(sender,receiver));
    }


    @Transactional()
    public void acceptFriend(String name, Long friendId){
        Member receiver = memberRepository.getUserByEmail(name);
        Member sender = memberRepository.getUserById(friendId);
        Friendship friendship = friendRepository.findByMemberAndFriend(sender, receiver)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
        friendship.accept();
        friendRepository.save(Friendship.finish(receiver,sender));
    }


    @Transactional()
    public void rejectFriend(String name, Long friendId){
        Member receiver = memberRepository.getUserByEmail(name);
        Friendship friendship = friendRepository.findByMemberAndFriend(friendId, receiver.getId())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        // friendship에 접근할때 sender를 조회하기 위한 쿼리가 한번 더 나감 (N+1)
        friendRepository.delete(friendship);
    }


    @Transactional(readOnly = true)
    public PageResponse<FriendResponse> getRequestedFriends(int page, String name){
        Member receiver = memberRepository.getUserByEmail(name);
        Page<MemberOnly> pageData = friendRepository.findAllByFriendAndStatus(PageRequest.of(page, 10), receiver, FriendStatus.WAITING);

        return PageResponse.<FriendResponse>builder()
                .content(pageData.getContent().stream()
                        .map(data -> FriendResponse.from(data.getMember()))
                        .collect(Collectors.toList()))
                .page(pageData.getNumber())
                .isLast(pageData.isLast())
                .build();
    }

}
