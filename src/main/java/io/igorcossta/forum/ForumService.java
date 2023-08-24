package io.igorcossta.forum;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ForumService {
    private final ForumRepository forumRepository;

    public List<Forum> getForumsByCollection(String title) {
        return forumRepository.findAllByCollection(title);
    }

    public boolean exists(String title) {
        return forumRepository.existsByTitle(title);
    }

    @Transactional
    public void plusOneTotalThreads(String forum) {
        forumRepository.incrementTotalThreads(forum);
    }

    @Transactional
    public void plusOneTotalMessages(String forum) {
        forumRepository.incrementTotalMessages(forum);
    }

    public Forum getForum(String title) {
        return forumRepository.findForumByTitle(title)
                .orElseThrow(() -> new ForumNotFoundException("forum %s not found".formatted(title)));
    }
}
