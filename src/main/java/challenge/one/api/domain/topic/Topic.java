package challenge.one.api.domain.topic;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "Topics")
@Table(name = "topics")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String title;
    private String message;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    private String status = "POSTED";

    private String author;
    private String course;


    public Topic (CreateTopicDto data) {
        this.title = data.title();
        this.message = data.message();
        this.author = data.author();
        this.course = data.course();
    }

}
