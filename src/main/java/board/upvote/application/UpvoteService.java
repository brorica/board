package board.upvote.application;

import board.member.application.MemberService;
import board.member.domain.Member;
import board.post.application.PostService;
import board.post.domain.Post;
import board.upvote.Upvote;
import board.upvote.UpvoteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
@Service
public class UpvoteService {

    private final UpvoteRepository upvoteRepository;

    private final MemberService memberService;

    private final PostService postService;

    public UpvoteService(final UpvoteRepository upvoteRepository,
                         final MemberService memberService,
                         final PostService postService) {
        this.upvoteRepository = upvoteRepository;
        this.memberService = memberService;
        this.postService = postService;
    }

    @Transactional
    public void toggleUpvote(final Long memberId, final Long postId) {
        final Member member = memberService.findMember(memberId);
        final Post post = postService.findPost(postId);
        Optional<Upvote> upVote = upvoteRepository.findByMemberAndPost(member, post);
        // 2명이 동시에 요청하면???
        if (upVote.isEmpty()) {
             upvoteRepository.save(new Upvote(member, post));
        }
        else {
            upVote.get().toggle();
        }
    }

    public Long getPostUpvoteCount(final Long postId) {
        return upvoteRepository.countByPost(postId);
    }
}
