package io.igorcossta.comment;

import io.igorcossta.util.PageableResponse;
import io.igorcossta.util.SuccessMessage;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @GetMapping
    @ResponseStatus(OK)
    @ResponseBody
    @PreAuthorize("permitAll()")
    public PageableResponse searchThreadComments(@RequestParam String thread,
                                                 @RequestParam(defaultValue = "0") int page) {
        return commentService.searchThreadComments(thread, page);
    }

    @PostMapping("/{thread}")
    @ResponseStatus(OK)
    @ResponseBody
    @PreAuthorize("hasAuthority('user_comment:create')")
    public SuccessMessage createComment(@PathVariable String thread, @RequestBody @Valid CreateNewComment request) {
        return commentService.createComment(request, thread);
    }
}
