package by.sacuta.exchangeService.restController;

import by.sacuta.exchangeService.model.dto.CourseDTO;
import by.sacuta.exchangeService.model.dto.LessonDTO;
import by.sacuta.exchangeService.model.dto.ProfileDTO;
import by.sacuta.exchangeService.model.model.Profile;
import by.sacuta.exchangeService.model.model.Course;
import by.sacuta.exchangeService.model.model.Lesson;
import by.sacuta.exchangeService.service.api.MyModelMapper;
import by.sacuta.exchangeService.service.api.ProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("rest/profile")
public class ProfileRestController {

    private final ProfileService profileService;
    private final MyModelMapper myModelMapper;

    public ProfileRestController(ProfileService profileService, MyModelMapper myModelMapper) {
        this.profileService = profileService;
        this.myModelMapper = myModelMapper;
    }


    @GetMapping(value = "/get/all")
    public ResponseEntity<List<ProfileDTO>> read() {

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
        final ProfileDTO clientDTO = myModelMapper.mapToProfileDTO(profileService.findByID(id));
        return clientDTO != null
                ? new ResponseEntity<>(clientDTO, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") long id) {
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
        profileService.save(myModelMapper.mapToProfile(profileDTO));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") long id, @RequestBody ProfileDTO profileDTO) {
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

