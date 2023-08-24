package io.igorcossta.thread;

import io.igorcossta.util.ThreadTypeConverter;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity(name = "thread")
public class Thread {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true, length = 200)
    private String title;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @Convert(converter = ThreadTypeConverter.class)
    private ThreadType type;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime closedAt;

    @Column(nullable = false)
    private String creator;

    @Column(nullable = false)
    private String forum; // the forum where that thread comes from (title)
}
