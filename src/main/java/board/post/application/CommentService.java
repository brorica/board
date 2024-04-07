package board.post.application;

import board.member.application.MemberService;
import board.member.domain.Member;
import board.post.domain.Comment;
import board.post.domain.Post;
import board.post.exception.CommentNotFoundException;
import board.post.persistence.CommentRepository;
import board.post.presentation.request.CommentCreateRequest;
import board.post.presentation.request.CommentUpdateRequest;
import board.post.presentation.response.CommentList;
import board.post.presentation.response.CommentResponse;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static board.common.exception.NotFoundException.COMMENT_NOT_FOUND;

@Transactional(readOnly = true)
@Service
public class CommentService {

    private final CommentRepository commentRepository;

    private final PostService postService;

    private final MemberService memberService;

    public CommentService(final CommentRepository commentRepository,
                          final PostService postService,
                          final MemberService memberService) {
        this.commentRepository = commentRepository;
        this.postService = postService;
        this.memberService = memberService;
    }

    @Transactional
    public Long createComment(final Long memberId, final Long postId, final CommentCreateRequest commentCreateRequest) {
        final Member member = memberService.findMember(memberId);
        final Post post = postService.findPost(postId);
        return commentRepository.save(new Comment(member, post, commentCreateRequest)).getId();
    }

    @Transactional
    public void deleteComment(final Long memberId, final Long commentId) {
        Comment comment = findComment(commentId);
        comment.delete(memberId);
    }

    public Comment findComment(final Long commentId) {
        return commentRepository.findById(commentId)
            .orElseThrow(() -> new CommentNotFoundException(COMMENT_NOT_FOUND));
    }

    @Transactional
    public void updateComment(final Long memberId, final Long commentId, final CommentUpdateRequest commentUpdateRequest) {
        Comment comment = findComment(commentId);
        comment.update(memberId, commentUpdateRequest);
    }

    public CommentList readCommentList(final Long postId) {
        List<CommentResponse> commentResponseList = commentRepository.findAllByPostId(postId)
                                                                     .stream()
                                                                     .map(CommentResponse::new)
                                                                     .collect(Collectors.toList());
        return new CommentList(commentResponseList);
    }

}
