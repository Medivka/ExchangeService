package by.sacuta.exchange.web.web;

import by.sacuta.exchange.dto.CourseDTO;
import by.sacuta.exchange.dto.LessonDTO;
import by.sacuta.exchange.dto.SectionDTO;
import by.sacuta.exchange.domain.model.Course;
import by.sacuta.exchange.domain.model.Lesson;
import by.sacuta.exchange.domain.model.Section;
import by.sacuta.exchange.service.CourseService;
import by.sacuta.exchange.service.MyModelMapper;
import by.sacuta.exchange.service.ProfileService;
import by.sacuta.exchange.service.SectionService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.LinkedList;
import java.util.List;

@Controller
public class CourseController {

    private final CourseService courseService;
    private final ProfileService profileService;
    private final MyModelMapper myModelMapper;
    private final SectionService sectionService;

    public CourseController(CourseService courseService, ProfileService profileService, MyModelMapper myModelMapper, SectionService sectionService) {
        this.courseService = courseService;
        this.profileService = profileService;
        this.myModelMapper = myModelMapper;
        this.sectionService = sectionService;
    }

    @GetMapping("/allCourses")
    public String findAll(Model model) {
        final List<CourseDTO> coursesDTO = new LinkedList<>();
        for (Course c : courseService.getAll()
        ) {
            coursesDTO.add(myModelMapper.mapToCourseDTO(c));
        }
        model.addAttribute("coursesDTO", coursesDTO);
        return "allCourseList";
    }

    @GetMapping("/courseAdd/{id}")
    public String addToCourse(@PathVariable("id") long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        courseService.addListener(courseService.findById(id), profileService.findByUsername(username));
        return "redirect:/myCourse";
    }

    @GetMapping("/courseList/courseAdd/{id}")
    public String addToCourses(@PathVariable("id") long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        courseService.addListener(courseService.findById(id), profileService.findByUsername(username));
        return "redirect:/myCourse";
    }

    @GetMapping("/courseDelete/{id}")
    public String deleteToCourse(@PathVariable("id") long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        courseService.deleteListener(courseService.findById(id), profileService.findByUsername(username));
        return "redirect:/myCourse";
    }

    @GetMapping("/courseList/courseDelete/{id}")
    public String deleteToCourses(@PathVariable("id") long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        courseService.deleteListener(courseService.findById(id), profileService.findByUsername(username));
        return "redirect:/myCourse";
    }

    @GetMapping("/lessonList/{id}")
    public String courseLesson(@PathVariable("id") long id, Model model) {
        final List<LessonDTO> lessonsDTO = new LinkedList<>();
        for (Lesson lesson : courseService.findById(id).getLessons()
        ) {
            lessonsDTO.add(myModelMapper.mapToLessonDTO(lesson));
        }
        model.addAttribute("lessonsDTO", lessonsDTO);
        return "lessonList";
    }

    @GetMapping("/section")
    public String findAllSection(Model model) {
        final List<SectionDTO> sectionDTO = new LinkedList<>();
        for (Section c : sectionService.getAll()
        ) {
            sectionDTO.add(myModelMapper.mapToSectionDTO(c));
        }
        model.addAttribute("sectionDTO", sectionDTO);
        return "sectionList";
    }

    @GetMapping("/courseList/{id}")
    public String sectionCourse(@PathVariable("id") long id, Model model) {
        final List<CourseDTO> coursesDTO = new LinkedList<>();
        for (Course course : courseService.getAll()
        ) {
            if (course.getSection().getId().equals(id)) {
                coursesDTO.add(myModelMapper.mapToCourseDTO(course));
            }
        }
        model.addAttribute("coursesDTO", coursesDTO);
        return "courseList";
    }
}
