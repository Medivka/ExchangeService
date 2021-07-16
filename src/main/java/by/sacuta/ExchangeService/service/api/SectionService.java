package by.sacuta.ExchangeService.service.api;

import by.sacuta.ExchangeService.model.enums.LessonStatus;
import by.sacuta.ExchangeService.model.model.Course;
import by.sacuta.ExchangeService.model.model.Lesson;
import by.sacuta.ExchangeService.model.model.Section;

import java.time.LocalDateTime;
import java.util.List;

public interface SectionService {
    List<Section> getAll();
    Section getById(Long id);

    void createNewSection(String name);
    void save(Section section);

    void delete(Long id);

    void update(Section section);
    List<Course> getAllCourse(Section section);
}
