package board.vote.presentation;

import board.vote.Vote;
import board.vote.application.VoteMemService;
import board.vote.application.VoteTransactionService;
import board.vote.presentation.dto.VoteCreateParam;
import board.vote.presentation.dto.VoteResponse;
import board.vote.presentation.dto.VoteStartParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RequestMapping("/api/votes")
@RestController
public class VoteManagerController {

    private final VoteTransactionService voteTransactionService;

    private final VoteMemService voteMemService;

    public VoteManagerController(final VoteTransactionService voteTransactionService,
                                 final VoteMemService voteMemService) {
        this.voteTransactionService = voteTransactionService;
        this.voteMemService = voteMemService;
    }

    @PostMapping
    public ResponseEntity<Void> createVote(@RequestBody final VoteCreateParam voteCreateParam) {
        final Long voteId = voteTransactionService.createVote(voteCreateParam);
        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(voteId).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PostMapping("/start")
    public ResponseEntity<Void> startVote(@RequestBody final VoteStartParam voteStartParam) {
        final Vote vote1 = voteTransactionService.findVote(voteStartParam.getVoteId1());
        final Vote vote2 = voteTransactionService.findVote(voteStartParam.getVoteId2());
        voteMemService.startVote(vote1, vote2);
        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{voteId}")
    public ResponseEntity<VoteResponse> getVoteInfo(@PathVariable("voteId") final Long voteId) {
        final Vote vote = voteTransactionService.findVote(voteId);
        return ResponseEntity.ok(new VoteResponse(vote));
    }

}
