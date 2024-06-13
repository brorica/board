package board.vote.presentation;

import board.vote.application.VoteMemService;
import board.vote.presentation.dto.VoteStatusResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/votes/status")
@RestController
public class VoteUserController {

    private final VoteMemService voteMemService;

    public VoteUserController(final VoteMemService voteMemService) {
        this.voteMemService = voteMemService;
    }

    @GetMapping
    public ResponseEntity<VoteStatusResponse> getCurrentStatus() {
        final VoteStatusResponse currentVoteStatus = voteMemService.getCurrentVoteStatus();
        return ResponseEntity.ok(currentVoteStatus);
    }

    @PutMapping("/{voteId}")
    public ResponseEntity<Void> increaseVote(@PathVariable("voteId") Long voteId,
                                             HttpServletRequest request) {
        final String sessionId = request.getSession().getId();
        final String clientIp = request.getRemoteAddr();
        try {
            voteMemService.increaseResult(voteId, clientIp, sessionId);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }


}
