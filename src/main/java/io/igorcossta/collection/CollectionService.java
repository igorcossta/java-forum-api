package io.igorcossta.collection;

import io.igorcossta.forum.ForumDto;
import io.igorcossta.forum.ForumService;
import io.igorcossta.mapper.ForumMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CollectionService {
    private final CollectionRepository collectionRepository;
    private final ForumService forumService;
    private final ForumMapper forumMapper;

    public List<CollectionToDisplayHomepage> findPublicCollections() {
        return collectionRepository.findAll()
                .stream()
                .map(this::buildContentForHomePage)
                .toList();
    }

    public List<ForumDto> findCollectionForums(String collection) {
        return forumService.getForumsByCollection(collection)
                .stream()
                .map(forumMapper::toDto)
                .toList();
    }

    private CollectionToDisplayHomepage buildContentForHomePage(Collection collection) {
        List<ForumDto> collectionForums = findCollectionForums(collection.getTitle());
        return new CollectionToDisplayHomepage(collection.getTitle(), collection.getDescription(), collectionForums);
    }
}
