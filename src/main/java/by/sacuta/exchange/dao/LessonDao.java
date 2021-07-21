package by.sacuta.exchange.dao;

import by.sacuta.exchange.domain.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonDao extends JpaRepository<Lesson,Long> {
}
