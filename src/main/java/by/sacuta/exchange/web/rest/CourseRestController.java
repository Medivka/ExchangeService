package by.sacuta.exchange.web.rest;

import by.sacuta.exchange.dto.CourseDTO;
import by.sacuta.exchange.domain.enums.CourseStatus;
import by.sacuta.exchange.domain.model.Course;
import by.sacuta.exchange.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("rest/course")
public class CourseRestController {

    private final MyModelMapper myModelMapper;
    private final CourseService courseService;
    private final LessonService lessonService;
    private final CommentService commentService;
    private final ProfileService profileService;


    public CourseRestController(MyModelMapper myModelMapper, CourseService courseService, LessonService lessonService, CommentService commentService, ProfileService profileService) {
        this.myModelMapper = myModelMapper;
        this.courseService = courseService;
        this.lessonService = lessonService;
        this.commentService = commentService;
        this.profileService = profileService;
    }

    @GetMapping(value = "/get/all")
    public ResponseEntity<List<CourseDTO>> getAll() {
        final List<CourseDTO> courseDTOList = new LinkedList<>();
        for (Course c : courseService.getAll()
        ) {
            courseDTOList.add(myModelMapper.mapToCourseDTO(c));
        }
        return courseDTOList != null && !courseDTOList.isEmpty()
                ? new ResponseEntity<>(courseDTOList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<CourseDTO> getById(@PathVariable(name = "id") long id) {
        final CourseDTO courseDTO = myModelMapper.mapToCourseDTO(courseService.findById(id));
        return courseDTO != null
                ? new ResponseEntity<>(courseDTO, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "id") long id) {
        boolean delete = false;
        final List<Course> courses = courseService.getAll();
        for (Course c : courses) {
            if (c.getId().equals(id)) {
                delete = true;
                courseService.delete(id);
            }
        }
        return delete
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @PostMapping(value = "/save")
    public ResponseEntity<?> save(@RequestBody CourseDTO courseDTO) {
        courseService.save(myModelMapper.mapToCourse(courseDTO));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") long id, @RequestBody CourseDTO courseDTO) {
        boolean update = false;
        final List<Course> courses = courseService.getAll();
        for (Course c : courses) {
            if (c.getId().equals(id)) {
                c = myModelMapper.mapToCourse(courseDTO);
                update = true;
                courseService.update(c);
            }
        }
        return update
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @GetMapping(value = "/add/{name}/{id}/{sd}")
    public ResponseEntity<CourseDTO> add(@PathVariable(name = "name") String name, @PathVariable(name = "id") long id, @PathVariable(name = "sd") long sd) {
        String names = name;
        switch (names) {
            case "lesson":
                courseService.addLesson(courseService.findById(id), lessonService.findById(sd));
                break;
            case "comment":
                courseService.addComment(courseService.findById(id), commentService.findById(sd));
                break;
            case "listener":
                courseService.addListener(courseService.findById(id), profileService.findByID(id));
                break;
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/getCourseStatus/{id}")
    public ResponseEntity<List<CourseDTO>> getByStatus(@PathVariable(name = "id") int id) {
        final List<CourseDTO> courseDTOS = new LinkedList<>();
        CourseStatus courseStatus=CourseStatus.OPEN;
        switch (id){
            case 1:  courseStatus=CourseStatus.OPEN;
                break;
            case 2 : courseStatus=CourseStatus.CLOSED;
                break;
        }
        for (Course c : courseService.getAll()
        ) {
            if (c.getCourseStatus().equals(courseStatus))
                courseDTOS.add(myModelMapper.mapToCourseDTO(c));
        }
        return courseDTOS != null && !courseDTOS.isEmpty()
                ? new ResponseEntity<>(courseDTOS, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/getByName/{name}")
    public ResponseEntity<List<CourseDTO>> getByName(@PathVariable(name = "name") String name) {
        final List<CourseDTO> courses = new LinkedList<>();
        for (Course c : courseService.getAll()
        ) {
            if (c.getName().contains(name))
                courses.add(myModelMapper.mapToCourseDTO(c));
        }
        return courses != null && !courses.isEmpty()
                ? new ResponseEntity<>(courses, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
