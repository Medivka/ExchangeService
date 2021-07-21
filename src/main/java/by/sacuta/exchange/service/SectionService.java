package by.sacuta.exchange.service;

import by.sacuta.exchange.domain.model.Course;
import by.sacuta.exchange.domain.model.Section;

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
