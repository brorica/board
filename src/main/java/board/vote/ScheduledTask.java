package board.vote;

import board.vote.application.VoteMemService;
import board.vote.application.VoteTransactionService;
import board.vote.presentation.dto.VoteStatusResponse;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTask {

    private final VoteMemService voteMemService;

    private final VoteTransactionService voteTransactionService;

    public ScheduledTask(final VoteMemService voteMemService, final VoteTransactionService voteTransactionService) {
        this.voteMemService = voteMemService;
        this.voteTransactionService = voteTransactionService;
    }

    /**
     * fixedDelay = 10000 -> 10초마다 실행
     * 자정때마다 기록을 저장하고 다음 투표로 넘어가게 하기
     */
    @Scheduled(fixedDelay = 10000)
    public void performTask() {
        // 특정 작업 수행
        System.out.println("Scheduled task executed!");
//        finishVote();
    }

    // 원자적으로 실행하게 하고 싶은데..
    private void finishVote() {
        final VoteStatusResponse currentVoteStatus = voteMemService.getCurrentVoteStatus();
        voteTransactionService.createVoteByVoteStatus(currentVoteStatus.getVoteStatusList());
        voteMemService.clearCurrentVoteHistory();
        // 준비된 투표를 적용
    }
}
