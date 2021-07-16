package by.sacuta.ExchangeService.service.api;

import by.sacuta.ExchangeService.model.model.Profile;
import by.sacuta.ExchangeService.model.model.Role;
import by.sacuta.ExchangeService.model.model.Course;
import by.sacuta.ExchangeService.model.model.Lesson;
import by.sacuta.ExchangeService.model.enums.ProfileStatus;

import java.util.List;


public interface ProfileService {

    Profile findByUsername(String username);

    void createNewProfile(String username, String password, String name, String lastname, Integer age, String email, String city, ProfileStatus status);

    void save(Profile clients);

    void delete(Long id);

    void update(Profile clients);

    Profile findByID(Long id);

    List<Profile> getAll();



    List<Course> getAllMyCourse(String username);

    List<Lesson> getActualLesson(String username);

    List<Profile> getAllSpeaker();
}
