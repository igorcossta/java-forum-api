package io.igorcossta.comment;

import io.igorcossta.user.UserDto;
import lombok.*;

@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CommentToDisplay {
    private String content;
    private UserDto owner;
    private String createdAt;
    private String thread;
}
