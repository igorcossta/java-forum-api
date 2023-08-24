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
public class ThreadUpdateRequest {
    @NotBlank(message = "You must specify the thread title to be updated")
    @Size(max = 200, message = "The thread title has a maximum of 200 characters")
    private String thread;

    @NotBlank(message = "Thread type must not be blank")
    @Size(max = 10, message = "The thread type should be QUESTION/SOLVED/CLOSED/GUIDE")
    private String threadType;
}
