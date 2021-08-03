package by.sacuta.exchange.web.rest;

import by.sacuta.exchange.config.MyCustomUserDetailsService;
import by.sacuta.exchange.domain.model.Course;
import by.sacuta.exchange.domain.model.Lesson;
import by.sacuta.exchange.domain.model.Profile;
import by.sacuta.exchange.dto.CourseDTO;
import by.sacuta.exchange.dto.LessonDTO;
import by.sacuta.exchange.dto.ProfileDTO;
import by.sacuta.exchange.service.MyModelMapper;
import by.sacuta.exchange.service.ProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("rest/profile")
public class ProfileRestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProfileRestController.class);

    private final ProfileService profileService;
    private final MyModelMapper myModelMapper;
    private final MyCustomUserDetailsService userDetailsService;

    public ProfileRestController(ProfileService profileService, MyModelMapper myModelMapper, MyCustomUserDetailsService userDetailsService) {
        this.profileService = profileService;
        this.myModelMapper = myModelMapper;
        this.userDetailsService = userDetailsService;
    }

    @GetMapping(value = "/get/all")
    public ResponseEntity<List<ProfileDTO>> read() {
        LOGGER.info("rest/profile/get/all ");
        final List<ProfileDTO> profileDTOS = new LinkedList<>();
        for (Profile profile : profileService.getAll()
        ) {
            profileDTOS.add(myModelMapper.mapToProfileDTO(profile));
        }
        return profileDTOS != null && !profileDTOS.isEmpty()
                ? new ResponseEntity<>(profileDTOS, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<ProfileDTO> read(@PathVariable(name = "id") long id) {
        LOGGER.info(String.format( "rest/profile/get/{%s} ",id));
        final ProfileDTO clientDTO = myModelMapper.mapToProfileDTO(profileService.findByID(id));
        return clientDTO != null
                ? new ResponseEntity<>(clientDTO, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") long id) {
        LOGGER.info(String.format( "rest/profile/delete/{%s} ",id));
        boolean delete = false;
        final List<Profile> clients = profileService.getAll();
        for (Profile clients1 : clients) {
            if (clients1.getId() == (id)) {
                delete = true;
                profileService.delete(id);
            }
        }
        return delete
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @PostMapping(value = "/save")
    public ResponseEntity<?> create(@RequestBody ProfileDTO profileDTO) {
        LOGGER.info(String.format( "rest/profile/save/{%s} ",profileDTO.getUsername()));
        userDetailsService.saveUser(myModelMapper.mapToProfile(profileDTO));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") long id, @RequestBody ProfileDTO profileDTO) {
        LOGGER.info(String.format( "rest/profile/update/{%s} ",id));
        boolean update = false;
        final List<Profile> profiles = profileService.getAll();
        for (Profile profile : profiles) {
            if (profile.getId() == (id)) {
                profile = myModelMapper.mapToProfile(profileDTO);
                update = true;
                profileService.update(profile);
            }
        }
        return update
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @GetMapping(value = "/get/getAllMyCourse/{id}")
    public ResponseEntity<List<CourseDTO>> getAllMyCourse(@PathVariable(name = "id") long id) {
        LOGGER.info(String.format( "rest/profile/getAllMyCourse/{%s} ",id));
        final List<CourseDTO> courseDTOS = new LinkedList<>();
        for (Course course : profileService.getAllMyCourse(profileService.findByID(id).getUsername())
        ) {
            courseDTOS.add(myModelMapper.mapToCourseDTO(course));
        }
        return courseDTOS != null && !courseDTOS.isEmpty()
                ? new ResponseEntity<>(courseDTOS, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/get/getActualLesson/{id}")
    public ResponseEntity<List<LessonDTO>> getActualLesson(@PathVariable(name = "id") long id) {
        LOGGER.info(String.format( "rest/profile/getActualLesson/{%s} ",id));
        final List<LessonDTO> lessonDTOS = new LinkedList<>();
        for (Lesson lesson : profileService.getActualLesson(profileService.findByID(id).getUsername())
        ) {
            lessonDTOS.add(myModelMapper.mapToLessonDTO(lesson));
        }
        return lessonDTOS != null && !lessonDTOS.isEmpty()
                ? new ResponseEntity<>(lessonDTOS, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

