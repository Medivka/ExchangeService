package by.sacuta.exchange.web.rest;

import by.sacuta.exchange.domain.enums.CourseStatus;
import by.sacuta.exchange.domain.model.Comment;
import by.sacuta.exchange.domain.model.Course;
import by.sacuta.exchange.dto.CommentDTO;
import by.sacuta.exchange.dto.CourseDTO;
import by.sacuta.exchange.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

import javax.annotation.PostConstruct;
import java.util.LinkedList;
import java.util.List;

@RequestScope                      /// на каждый запрос новый бин контроллера
@RestController
@RequestMapping("rest/course")
public class CourseRestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CourseRestController.class);

    @PostConstruct
    public void  init(){
        System.out.println("restcontroller create");
    }
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

    @GetMapping(value = "/all")
    public ResponseEntity<List<CourseDTO>> getAll() {
        LOGGER.info("rest/course/get/all ");
        final List<CourseDTO> courseDTOList = new LinkedList<>();
        for (Course c : courseService.getAll()
        ) {
            courseDTOList.add(myModelMapper.mapToCourseDTO(c));
        }
        return courseDTOList != null && !courseDTOList.isEmpty()
                ? new ResponseEntity<>(courseDTOList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CourseDTO> getById(@PathVariable(name = "id") long id) {
        LOGGER.info(String.format( "rest/course/get/{%s} ",id));
        final CourseDTO courseDTO = myModelMapper.mapToCourseDTO(courseService.findById(id));
        return courseDTO != null
                ? new ResponseEntity<>(courseDTO, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "id") long id) {
        LOGGER.info(String.format( "rest/course/delete/{%s} ",id));
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
        LOGGER.info(String.format( "rest/course/save {%s} ",courseDTO.getName()));
        courseService.save(myModelMapper.mapToCourse(courseDTO));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") long id, @RequestBody CourseDTO courseDTO) {
        LOGGER.info(String.format( "rest/course/update/{%s} ",id));

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
        LOGGER.info(String.format( "rest/course/getCourseStatus/{%s} ",id));
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
        LOGGER.info(String.format( "rest/course/getByName/{%s} ",name));
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
    @GetMapping(value = "/getcoursecomment/{id}")
    public ResponseEntity<List<CommentDTO>> getCourseComment(@PathVariable(name = "id") long    id) {
        LOGGER.info(String.format( "rest/course/getcoursecomment/{%s} ",id));
        List<CommentDTO>comments = new LinkedList<>();
        for (Comment c: courseService.findById(id).getComments()
             ) {comments.add(myModelMapper.mapToCommentDTO(c));

        }
        return comments != null && !comments.isEmpty()
                ? new ResponseEntity<>(comments, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
