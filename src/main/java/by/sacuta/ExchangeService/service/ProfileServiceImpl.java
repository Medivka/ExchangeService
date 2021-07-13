package by.sacuta.ExchangeService.service;

import by.sacuta.ExchangeService.dao.CourseDao;
import by.sacuta.ExchangeService.dao.ProfileDao;
import by.sacuta.ExchangeService.dao.RoleDao;
import by.sacuta.ExchangeService.model.model.Course;
import by.sacuta.ExchangeService.model.model.Lesson;
import by.sacuta.ExchangeService.model.model.Profile;
import by.sacuta.ExchangeService.model.model.Role;
import by.sacuta.ExchangeService.model.enums.ProfileStatus;
import by.sacuta.ExchangeService.service.api.ProfileService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfileServiceImpl implements ProfileService {

    private final ProfileDao profileDao;
    private final RoleDao roleDao;
    private final CourseDao courseDao;

    public ProfileServiceImpl(ProfileDao profileDao, RoleDao roleDao, CourseDao courseDao) {
        this.profileDao = profileDao;
        this.roleDao = roleDao;
        this.courseDao = courseDao;
    }

    @Override
    public Profile findByUsername(String username){

        return profileDao.findByUsername(username);
    }

    @Override
    public void createNewProfile(String username, String password, String name, String lastname, Integer age, String email, String city, ProfileStatus status) {
       Profile client = new Profile(username, password, name, lastname, age, email, city, status);

        save(client);
    }

    @Override
    public void save(Profile profile) {
        List<Profile> profileList = profileDao.findAll();
        boolean b = true;
        for (int i = 0; i < profileList.size(); i++) {
            Profile prof = profileList.get(i);
            if (prof.getUsername().equals(profile.getUsername())) {
                b = false;
            }
        }
        if (b) {
            List<Role> roles = new LinkedList<>();
            roles.add(roleDao.getById(1l));
            profile.setRoles(roles);
            profileDao.save(profile);
        }
    }

    @Override
    public void delete(Long id) {
        profileDao.delete(profileDao.getById(id));

    }

    @Override
    public void update(Profile profile) {
        profileDao.save(profile);

    }

    @Override
    public Profile findByID(Long id) {
        return profileDao.getById(id);
    }

    @Override
    public List<Profile> getAll() {
        return profileDao.findAll();
    }

    @Override
    public Profile getByUsername(String username) {

        return profileDao.findByUsername(username);
    }

    @Override
    public List<Course> getAllMyCourse(String username) {

        List<Course> myCourses = new LinkedList<>();
        List<Course> allCourses = courseDao.findAll();
        for (Course co : allCourses) {
           List<Profile> profileList=profileDao.findAll();
            for (Profile cli : profileList) {
                if (cli.getUsername().equals(username)) {
                    System.out.println(username);
                    myCourses.add(co);
                }
            }
        }

        myCourses.stream().sorted((o1, o2) -> o1.getStartCourse().compareTo(o2.getStartCourse())).collect(Collectors.toList());
        return myCourses;
    }

    @Override
    public List<Lesson> getActualLesson(String username) {
        List<Lesson> lessons = new LinkedList<>();
        List<Course> courses = getAllMyCourse(username);
        for (Course co : courses) {
            List<Lesson> lessons1 = co.getLessons();
            for (Lesson le : lessons1) {
                if (le.getLocalDateTime().isAfter(LocalDateTime.now())) {
                    lessons.add(le);
                }
            }
        }
        return lessons.stream().sorted((a, b) -> a.getLocalDateTime().compareTo(b.getLocalDateTime())).collect(Collectors.toList());
    }

    @Override
    public List<Profile> getAllSpeaker() {
        List<Profile> speakers = new LinkedList<>();
        for (Profile prof : profileDao.findAll()
        ) {
            if (prof.getStatus().equals(ProfileStatus.SPEAKER)) {
                speakers.add(prof);
            }
        }
        return speakers;
    }
}
