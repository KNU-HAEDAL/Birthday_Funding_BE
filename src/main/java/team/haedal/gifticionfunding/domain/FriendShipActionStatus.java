package team.haedal.gifticionfunding.domain;

public enum FriendShipActionStatus {
    REQUEST, // 요청
    ACCEPT,  // 수락
    REJECT, // 거절
    CANCEL, // 취소
    RECOMMEND; // 추천

    /**
     * 수락 가능 여부 <br>
     * [REQUEST]요청, [RECOMMEND]추천 상태일 때만 수락 가능
     */
    public boolean canAccept() {
        return this == REQUEST || this == RECOMMEND;
    }
}
