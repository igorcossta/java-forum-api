package io.igorcossta.thread;

import io.igorcossta.util.PageableResponse;
import io.igorcossta.util.SuccessMessage;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/thread")
@RequiredArgsConstructor
public class ThreadController {
    private final ThreadService threadService;

    @GetMapping("/find")
    @ResponseStatus(OK)
    @ResponseBody
    @PreAuthorize("permitAll()")
    public PageableResponse findThreads(@RequestParam(required = false) String forum,
                                        @RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "3") int size) {
        return threadService.findAllThreads(forum, page, size);
    }

    @PostMapping
    @ResponseStatus(OK)
    @ResponseBody
    @PreAuthorize("hasAuthority('user_thread:create')")
    public SuccessMessage createThread(@RequestBody @Valid CreateNewThread request) {
        return threadService.createThread(request);
    }

    @PutMapping
    @ResponseStatus(OK)
    @ResponseBody
    @PreAuthorize("hasAuthority('user_thread:update')")
    public SuccessMessage updateThreadStatus(@RequestBody @Valid ThreadUpdateRequest threadUpdateRequest) {
        return threadService.updateThreadStatus(threadUpdateRequest);
    }
}
