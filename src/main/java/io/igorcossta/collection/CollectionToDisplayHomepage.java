package io.igorcossta.collection;

import io.igorcossta.forum.ForumDto;
import lombok.*;

import java.util.List;

@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CollectionToDisplayHomepage {
    private String title;
    private String description;
    private List<ForumDto> forums;
}
