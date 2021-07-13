package by.sacuta.ExchangeService.dao;

import by.sacuta.ExchangeService.model.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseDao extends JpaRepository<Course,Long> {
}
