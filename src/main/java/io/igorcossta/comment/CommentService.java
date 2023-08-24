package io.igorcossta.comment;

import io.igorcossta.forum.Forum;
import io.igorcossta.forum.ForumService;
import io.igorcossta.mapper.CommentMapper;
import io.igorcossta.thread.Thread;
import io.igorcossta.thread.ThreadService;
import io.igorcossta.user.User;
import io.igorcossta.user.UserService;
import io.igorcossta.util.PageableResponse;
import io.igorcossta.util.SuccessMessage;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserService userService;
    private final ForumService forumService;
    private final ThreadService threadService;
    private final CommentMapper commentMapper;

    public PageableResponse searchThreadComments(String thread, int page) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Comment> pageComment = commentRepository.findAllByThread(thread, pageable);

        List<CommentToDisplay> content = pageComment.getContent()
                .stream()
                .map(i -> commentMapper.toCommentDisplay(i, userService.findUserByUsername(i.getOwner())))
                .collect(Collectors.toList());
        return new PageableResponse()
                .put("comment", content)
                .build(pageComment);
    }

    @Transactional
    public SuccessMessage createComment(CreateNewComment request, String thread) {
        Thread theThread = threadService.getThread(thread);
        Forum forum = forumService.getForum(theThread.getForum());
        forumService.plusOneTotalMessages(forum.getTitle());

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Comment entity = commentMapper.fromNewComment(request, user.getUsername(), thread);

        commentRepository.save(entity);
        return new SuccessMessage("SUCCESS", "the comment was created successfully");
    }
}
