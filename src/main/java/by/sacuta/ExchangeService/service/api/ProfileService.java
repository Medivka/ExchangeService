package by.sacuta.ExchangeService.service.api;

import by.sacuta.ExchangeService.model.model.Role;
import by.sacuta.ExchangeService.model.model.Course;
import by.sacuta.ExchangeService.model.model.Lesson;
import by.sacuta.ExchangeService.model.enums.ProfileStatus;

import java.util.List;


public interface ProfileService {
    Role.Profile findByUsername(String username);

    void createNewProfile(String username, String password, String name, String lastname, Integer age, String email, String city, ProfileStatus status);

    void save(Role.Profile clients);

    void delete(Long id);

    void update(Role.Profile clients);

    Role.Profile findByID(Long id);

    List<Role.Profile> getAll();

    Role.Profile getByUsername(String username);

    List<Course> getAllMyCourse(String username);

    List<Lesson> getActualLesson(String username);

    List<Role.Profile> getAllSpeaker();
}
