package board.vote.application;

import board.vote.Vote;
import board.vote.application.dto.VoteStatus;
import board.vote.presentation.dto.VoteStatusResponse;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class VoteMemService {

    private final ConcurrentHashMap<Long, VoteStatus> voteInfoMap;
    private final ConcurrentHashMap<String, Long> votedSessionMap;
    private Long voteId1;
    private Long voteId2;

    public VoteMemService() {
        this.voteInfoMap = new ConcurrentHashMap<>(43);
        this.votedSessionMap = new ConcurrentHashMap<>(2731);
    }

    public void startVote(final Vote vote1, final Vote vote2) {
        voteInfoMap.put(vote1.getId(), new VoteStatus(vote1));
        voteId1 = vote1.getId();
        voteInfoMap.put(vote2.getId(), new VoteStatus(vote2));
        voteId2 = vote2.getId();
    }

    public void increaseResult(final Long voteId, final String sessionId) {
        checkDuplicateVote(sessionId);
        votedSessionMap.put(sessionId, voteId);
        VoteStatus voteStatus = voteInfoMap.get(voteId);
        voteStatus.validateVoteDate();
        voteStatus.increaseVoteCount();
    }

    private void checkDuplicateVote(final String sessionId) {
        if(votedSessionMap.containsKey(sessionId)) {
            throw new RuntimeException("이미 투표하셨습니다.");
        }
    }

    public VoteStatusResponse getCurrentVoteStatus() {
        VoteStatusResponse voteStatusResponse = new VoteStatusResponse();
        voteStatusResponse.addVoteStatus(voteInfoMap.get(voteId1));
        voteStatusResponse.addVoteStatus(voteInfoMap.get(voteId2));
        return voteStatusResponse;
    }
}
