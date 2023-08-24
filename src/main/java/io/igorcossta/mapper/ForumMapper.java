package io.igorcossta.mapper;

import io.igorcossta.forum.Forum;
import io.igorcossta.forum.ForumDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface ForumMapper {

    ForumDto toDto(Forum forum);
}
