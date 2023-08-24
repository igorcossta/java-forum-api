package io.igorcossta.thread;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CreateNewThread {
    @NotBlank(message = "You must set an title for this thread")
    @Size(min = 10, max = 200, message = "Title must have at least 10 characters (max 200)")
    private String title;

    @NotBlank(message = "Thread type must not be blank")
    @Size(max = 10, message = "The thread type should be QUESTION/SOLVED/CLOSED/GUIDE")
    private String type;

    @NotBlank(message = "You must specify where this thread comes from")
    @Size(max = 200, message = "Forum title has a maximum of 200 characters")
    private String forum;

    @NotBlank(message = "You must write an content before create an thread")
    private String content;
}
