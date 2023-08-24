package io.igorcossta.collection;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/collection")
@RequiredArgsConstructor
public class CollectionController {
    private final CollectionService collectionService;

    @GetMapping
    @ResponseStatus(OK)
    @ResponseBody
    @PreAuthorize("permitAll()")
    public List<CollectionToDisplayHomepage> getPublicCollections() {
        return collectionService.findPublicCollections();
    }
}
