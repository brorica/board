package board.vote.presentation.dto;

import board.vote.Vote;

import java.time.LocalDateTime;

public class VoteResponse {

    private final Long id;

    private final String name;

    private final Long voteCount;

    private final LocalDateTime startAt;

    private final LocalDateTime endAt;

    public VoteResponse(final Vote vote) {
        this.id = vote.getId();
        this.name = vote.getName();
        this.voteCount = vote.getVoteCount();
        this.startAt = vote.getStartAt();
        this.endAt = vote.getEndAt();
    }

    public Long getId() {
        return id;
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
