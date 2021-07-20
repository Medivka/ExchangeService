package by.sacuta.exchangeService.service.api;

import by.sacuta.exchangeService.model.model.Course;
import by.sacuta.exchangeService.model.model.Section;

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
