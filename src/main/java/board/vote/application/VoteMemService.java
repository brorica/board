package board.vote.application;

import board.vote.Vote;
import board.vote.application.dto.VoteStatus;
import board.vote.presentation.dto.VoteStatusResponse;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class VoteMemService {

    private final ConcurrentHashMap<Long, VoteStatus> voteInfoMap;
    private final Set<String> voteRecord;
    private Long voteId1;
    private Long voteId2;

    public VoteMemService() {
        this.voteInfoMap = new ConcurrentHashMap<>(43);
        this.voteRecord = new HashSet<>(2731);
    }

    public void startVote(final Vote vote1, final Vote vote2) {
        voteInfoMap.put(vote1.getId(), new VoteStatus(vote1));
        voteId1 = vote1.getId();
        voteInfoMap.put(vote2.getId(), new VoteStatus(vote2));
        voteId2 = vote2.getId();
    }

    public void increaseResult(final Long voteId, final String clientIp, final String sessionId) {
        checkDuplicateVote(clientIp, sessionId);
        voteRecord.add(clientIp + ":" + sessionId);
        VoteStatus voteStatus = voteInfoMap.get(voteId);
        voteStatus.validateVoteDate();
        voteStatus.increaseVoteCount();
    }

    /**
     * IP 주소 + 세션 값으로 중복 투표 탐색
     */
    private void checkDuplicateVote(final String clientIp, final String sessionId) {
        String recordKey = clientIp + ":" + sessionId;
        if(voteRecord.contains(recordKey)) {
            throw new RuntimeException("이미 투표하셨습니다.");
        }
        voteRecord.add(recordKey);
    }

    /**
     * 현재 투표 상태를 반환
     */
    public VoteStatusResponse getCurrentVoteStatus() {
        VoteStatusResponse voteStatusResponse = new VoteStatusResponse();
        voteStatusResponse.addVoteStatus(voteInfoMap.get(voteId1));
        voteStatusResponse.addVoteStatus(voteInfoMap.get(voteId2));
        return voteStatusResponse;
    }

    public void clearCurrentVoteHistory() {
        voteInfoMap.clear();
        voteRecord.clear();
//        voteId1 = 0L;
//        voteId2 = 0L;
    }
}
