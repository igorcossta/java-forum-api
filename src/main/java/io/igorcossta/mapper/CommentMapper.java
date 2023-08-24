package io.igorcossta.mapper;

import io.igorcossta.comment.Comment;
import io.igorcossta.comment.CommentToDisplay;
import io.igorcossta.comment.CreateNewComment;
import io.igorcossta.user.User;
import io.igorcossta.util.PrettyTimeFormatter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public abstract class CommentMapper {
    @Autowired
    protected UserMapper userMapper;

    @Autowired
    protected PrettyTimeFormatter prettyTimeFormatter;

    @Mapping(target = "owner", expression = "java(userMapper.toDto(user))")
    @Mapping(target = "createdAt", expression = "java(prettyTimeFormatter.format(comment.getCreatedAt()))")
    public abstract CommentToDisplay toCommentDisplay(Comment comment, User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "updatedAt", expression = "java(null)")
    @Mapping(target = "owner", expression = "java(owner)")
    @Mapping(target = "thread", expression = "java(thread)")
    public abstract Comment fromNewComment(CreateNewComment dto, String owner, String thread);
}
