package by.sacuta.ExchangeService.dao;

import by.sacuta.ExchangeService.model.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonDao extends JpaRepository<Lesson,Long> {
}
