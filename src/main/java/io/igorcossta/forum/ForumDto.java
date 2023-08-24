package io.igorcossta.forum;

import lombok.*;

@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ForumDto {
    private String title;
    private String description;
    private Integer totalThreads;
    private Integer totalMessages;
}
