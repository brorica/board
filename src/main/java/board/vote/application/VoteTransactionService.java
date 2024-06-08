package board.vote.application;

import board.vote.Vote;
import board.vote.VoteRepository;
import board.vote.presentation.dto.VoteCreateParam;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public Vote findVote(final Long voteId) {
        return voteRepository.findById(voteId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 투표 ID 입니다. : " + voteId));
    }

}
