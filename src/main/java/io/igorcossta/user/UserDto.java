package io.igorcossta.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@JsonInclude(NON_EMPTY)
public class UserDto {
    private String username;
    private String firstName;
    private String lastName;
    private String createdAt;
    private Role role;
}
