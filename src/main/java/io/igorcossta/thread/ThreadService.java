package io.igorcossta.thread;

import io.igorcossta.forum.ForumNotFoundException;
import io.igorcossta.forum.ForumService;
import io.igorcossta.mapper.ThreadMapper;
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
public class ThreadService {
    private final ThreadRepository threadRepository;
    private final ForumService forumService;
    private final UserService userService;
    private final ThreadMapper threadMapper;

    public PageableResponse findAllThreads(String forum, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Thread> pageThread;
        if (forum == null) {
            pageThread = threadRepository.findAll(pageable);
        } else {
            pageThread = threadRepository.findAllByForum(forum, pageable);
        }

        List<Thread> entities = pageThread.getContent();
        List<ThreadToDisplay> content = entities.stream()
                .map(entity -> threadMapper.toThreadDisplay(entity, userService.findUserByUsername(entity.getCreator())))
                .collect(Collectors.toList());

        return new PageableResponse()
                .put("thread", content)
                .build(pageThread);
    }

    @Transactional
    public SuccessMessage createThread(CreateNewThread request) {
        boolean forumExists = forumService.exists(request.getForum());
        if (!forumExists) {
            throw new ForumNotFoundException("forum %s not found".formatted(request.getForum()));
        }

        boolean threadTitleExists = threadRepository.existsByTitle(request.getTitle());
        if (threadTitleExists) {
            throw new DuplicateThreadException("thread '%s' already exists. Please try another one or check for the existing one".formatted(request.getTitle()));
        }

        // todo: check if TYPE of thread exists!
        forumService.plusOneTotalThreads(request.getForum());

        User theCreator = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Thread newThread = threadMapper.fromNewThread(request, theCreator);

        threadRepository.save(newThread);
        return new SuccessMessage("SUCCESS",
                "thread '%s' created successfully".formatted(request.getTitle()));
    }

    public boolean exists(String thread) {
        return threadRepository.existsByTitle(thread);
    }

    public Thread getThread(String thread) {
        return threadRepository.findThreadByTitle(thread).orElseThrow(
                () -> new ThreadNotFoundException("thread %s not found".formatted(thread)));
    }

    @Transactional
    public SuccessMessage updateThreadStatus(ThreadUpdateRequest threadUpdateRequest) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Thread thread = getThread(threadUpdateRequest.getThread());

        if (!user.getUsername().equals(thread.getCreator())) {
            throw new ThreadNotOwnerException("you are not the owner of the thread %s".formatted(threadUpdateRequest.getThread()));
        }

        thread.setType(ThreadType.valueOf(threadUpdateRequest.getThreadType()));
        threadRepository.save(thread);

        return new SuccessMessage("SUCCESS",
                "thread status updated to %s".formatted(threadUpdateRequest.getThreadType()));
    }
}
