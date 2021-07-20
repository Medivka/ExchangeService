package by.sacuta.exchangeService.dao;

import by.sacuta.exchangeService.model.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonDao extends JpaRepository<Lesson,Long> {
}
