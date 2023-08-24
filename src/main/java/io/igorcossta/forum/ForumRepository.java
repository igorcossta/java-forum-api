package io.igorcossta.forum;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ForumRepository extends JpaRepository<Forum, UUID> {

    List<Forum> findAllByCollection(String collection);

    boolean existsByTitle(String title);

    Optional<Forum> findForumByTitle(String title);

    @Modifying
    @Query("UPDATE forum f SET f.totalThreads = f.totalThreads + 1 WHERE f.title = :forum")
    void incrementTotalThreads(String forum);

    @Modifying
    @Query("UPDATE forum f SET f.totalMessages = f.totalMessages + 1 WHERE f.title = :forum")
    void incrementTotalMessages(String forum);
}
