package challenge.one.api.domain.topic;

import challenge.one.api.util.StringValidator;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

@Entity(name = "Topics")
@Table(name = "topics")
@Getter
@Setter
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

    public void update (UpdateTopicDto data) {
        if (!StringValidator.isNullOrEmpty(data.title())) {
            setTitle(data.title());
        }
        if (!StringValidator.isNullOrEmpty(data.message())) {
            setMessage(data.message());
        }
        if (!StringValidator.isNullOrEmpty(data.course())) {
            setCourse(data.course());
        }
        if (!StringValidator.isNullOrEmpty(data.author())) {
            setAuthor(data.author());
        }
    }

}
