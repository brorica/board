package board.vote.application.dto;

import board.vote.Vote;

import java.time.LocalDateTime;

public class VoteStatus {

    private final String name;

    private Long voteCount;

    private final LocalDateTime startAt;

    private final LocalDateTime endAt;

    public VoteStatus(final Vote vote) {
        this.name = vote.getName();
        this.voteCount = 0L;
        this.startAt = vote.getStartAt();
        this.endAt = vote.getEndAt();
    }

    public void validateVoteDate() {
        final LocalDateTime now = LocalDateTime.now();
        if(now.isBefore(startAt) || now.isAfter(endAt)) {
            throw new RuntimeException("종료된 투표입니다.");
        }
    }

    public void increaseVoteCount() {
        this.voteCount += 1;
    }

    public String getName() {
        return name;
    }

    public Long getVoteCount() {
        return voteCount;
    }

    public LocalDateTime getStartAt() {
        return startAt;
    }

    public LocalDateTime getEndAt() {
        return endAt;
    }

}
