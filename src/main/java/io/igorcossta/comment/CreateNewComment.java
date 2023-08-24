package io.igorcossta.comment;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CreateNewComment {
    @NotBlank(message = "You must write an content before create a comment")
    private String content;
}
