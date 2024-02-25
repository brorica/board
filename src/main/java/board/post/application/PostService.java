package board.post.application;

import board.member.application.MemberService;
import board.member.domain.Member;
import board.post.domain.Post;
import board.post.persistence.PostRepository;
import board.post.presentation.request.PostCreateRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
public class PostService {

    private final PostRepository postRepository;

    private final MemberService memberService;

    public PostService(final PostRepository postRepository, final MemberService memberService) {
        this.postRepository = postRepository;
        this.memberService = memberService;
    }

    public Long createPost(final Long memberId, final PostCreateRequest postCreateRequest) {
        final Member member = memberService.getMemberById(memberId);
        return postRepository.save(new Post(member, postCreateRequest))
                             .getId();
    }

    public Post findPost(final Long id) {
        return postRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("존재하지 않는 글입니다."));
    }

    public void DeletePost(final Long memberId, final Long postId) {
        final Post post = findPost(postId);
        post.validateAndDelete(memberId);
    }
}
