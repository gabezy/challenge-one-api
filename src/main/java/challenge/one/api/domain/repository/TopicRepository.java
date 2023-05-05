package challenge.one.api.domain.repository; import challenge.one.api.domain.topic.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.Year;

public interface TopicRepository extends JpaRepository<Topic, String> {
    Topic findTopicByTitleAndMessage(String title, String message);
    @Query("SELECT t From Topics t WHERE t.status = 'POSTED'")
    Page<Topic> findAllByStatusEqualsPosted(Pageable pageable);
    @Query("SELECT t FROM Topics t WHERE LOWER(t.course) LIKE %:course% AND EXTRACT(YEAR FROM t.createdAt) = :year AND t.status = 'POSTED' ")
    Page<Topic> findAllByCourseAndCreatedAtYearAndStatusEqualsPosted(Pageable pageable, String course, int year);
    @Query("SELECT t FROM Topics t WHERE LOWER(t.course) LIKE %:course% AND t.status = 'POSTED'")
    Page<Topic> findAllByCourseAndStatusEqualsPosted(Pageable pageable, String course);
    @Query("SELECT t FROM Topics t WHERE EXTRACT(YEAR FROM t.createdAt) = :year AND t.status = 'POSTED'")
    Page<Topic> findAllByYearAndStatusEqualsPosted(Pageable pageable, int year);
}
