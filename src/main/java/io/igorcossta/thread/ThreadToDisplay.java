package io.igorcossta.thread;

import io.igorcossta.user.UserDto;
import lombok.*;

@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ThreadToDisplay {
    private String title;
    private ThreadType type;
    private String threadCreatedAt;
    private String forum;
    //    private Integer totalComments; // todo: add total comments
    private UserDto owner;
}
