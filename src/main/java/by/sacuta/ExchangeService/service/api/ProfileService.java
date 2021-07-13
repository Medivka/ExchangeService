package by.sacuta.ExchangeService.service.api;

import by.sacuta.ExchangeService.model.Course;
import by.sacuta.ExchangeService.model.Lesson;
import by.sacuta.ExchangeService.model.Profile;
import by.sacuta.ExchangeService.model.enums.ProfileStatus;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProfileService {
    Profile findByUsername(String username);

    void createNewProfile(String username, String password, String name, String lastname, Integer age, String email, String city, ProfileStatus status);

    void save(Profile clients);

    void delete(Long id);

    void update(Profile clients);

    Profile findByID(Long id);

    List<Profile> getAll();

    Profile getByUsername(String username);

    List<Course> getAllMyCourse(String username);

    List<Lesson> getActualLesson(String username);

    List<Profile> getAllSpeaker();
}
