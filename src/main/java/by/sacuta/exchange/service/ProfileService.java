package by.sacuta.exchange.service;

import by.sacuta.exchange.domain.model.Profile;
import by.sacuta.exchange.domain.model.Course;
import by.sacuta.exchange.domain.model.Lesson;
import by.sacuta.exchange.domain.enums.ProfileStatus;

import java.util.List;


public interface ProfileService {

    boolean existsByUsername(String username);

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

    void getRoleAdmin(Profile profile);
}
