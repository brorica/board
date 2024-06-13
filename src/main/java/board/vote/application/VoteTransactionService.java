package board.vote.application;

import board.vote.Vote;
import board.vote.VoteRepository;
import board.vote.application.dto.VoteStatus;
import board.vote.presentation.dto.VoteCreateParam;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@Service
public class VoteTransactionService {

    private final VoteRepository voteRepository;

    public VoteTransactionService(final VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    @Transactional
    public Long createVote(final VoteCreateParam voteCreateParam) {
        return voteRepository.save(new Vote(voteCreateParam)).getId();
    }

    @Transactional
    public void createVoteByVoteStatus(final List<VoteStatus> voteStatusList) {
        for(VoteStatus voteStatus: voteStatusList) {
            voteRepository.save(new Vote(voteStatus));
        }
    }

    public Vote findVote(final Long voteId) {
        return voteRepository.findById(voteId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 투표 ID 입니다. : " + voteId));
    }

}
