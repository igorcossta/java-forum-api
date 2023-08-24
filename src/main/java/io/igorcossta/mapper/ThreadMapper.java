package io.igorcossta.mapper;

import io.igorcossta.thread.CreateNewThread;
import io.igorcossta.thread.Thread;
import io.igorcossta.thread.ThreadToDisplay;
import io.igorcossta.user.User;
import io.igorcossta.util.PrettyTimeFormatter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public abstract class ThreadMapper {
    @Autowired
    protected UserMapper userMapper;

    @Autowired
    protected PrettyTimeFormatter prettyTimeFormatter;

    @Mapping(target = "threadCreatedAt", expression = "java(prettyTimeFormatter.format(entity.getCreatedAt()))")
    @Mapping(target = "owner", expression = "java(userMapper.toDto(user))")
    public abstract ThreadToDisplay toThreadDisplay(Thread entity, User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "closedAt", expression = "java(null)")
    @Mapping(target = "creator", expression = "java(theCreator.getUsername())")
    public abstract Thread fromNewThread(CreateNewThread dto, User theCreator);
}
