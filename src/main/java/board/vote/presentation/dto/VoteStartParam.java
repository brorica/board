package board.vote.presentation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VoteStartParam {

    private final Long voteId1;

    private final Long voteId2;

    public VoteStartParam(@JsonProperty("voteId1") Long voteId1,
                          @JsonProperty("voteId2") Long voteId2) {
        this.voteId1 = voteId1;
        this.voteId2 = voteId2;
    }

    public Long getVoteId1() {
        return voteId1;
    }

    public Long getVoteId2() {
        return voteId2;
    }
}
