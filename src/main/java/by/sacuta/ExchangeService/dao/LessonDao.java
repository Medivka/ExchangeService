package by.sacuta.ExchangeService.dao;

import by.sacuta.ExchangeService.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonDao extends JpaRepository<Lesson,Long> {
}
