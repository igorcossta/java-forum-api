package io.igorcossta.thread;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ThreadRepository extends JpaRepository<Thread, UUID> {

    Page<Thread> findAllByForum(String forum, Pageable pageable);

    boolean existsByTitle(String title);

    Optional<Thread> findThreadByTitle(String title);
}
