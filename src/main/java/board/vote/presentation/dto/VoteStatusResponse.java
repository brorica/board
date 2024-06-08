package board.vote.presentation.dto;

import board.vote.application.dto.VoteStatus;

import java.util.ArrayList;
import java.util.List;

public class VoteStatusResponse {

    private List<VoteStatus> voteStatusList = new ArrayList<>();

    public void addVoteStatus(final VoteStatus voteStatus) {
        voteStatusList.add(voteStatus);
    }

    public List<VoteStatus> getVoteStatusList() {
        return voteStatusList;
    }
}
