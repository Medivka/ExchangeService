package by.sacuta.exchange.web.web;

import by.sacuta.exchange.dto.LessonDTO;
import by.sacuta.exchange.domain.model.Lesson;
import by.sacuta.exchange.service.LessonService;
import by.sacuta.exchange.service.MyModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LessonController {

    private final LessonService lessonService;
    private final MyModelMapper myModelMapper;

    public LessonController(LessonService lessonService, MyModelMapper myModelMapper) {
        this.lessonService = lessonService;
        this.myModelMapper = myModelMapper;
    }

    @GetMapping("/lesson-update/{id}")
    public String updateLessonDTO(@PathVariable("id") Long id, Model model) {
        LessonDTO lessonDTO = myModelMapper.mapToLessonDTO(lessonService.findById(id));
        model.addAttribute("lessonDTO", lessonDTO);
        return "/lesson-update";
    }

    @PostMapping("/lesson-update")
    public String updateLessonsDTO(LessonDTO lessonDTO) {
         lessonService.update(myModelMapper.mapToLesson(lessonDTO));
        return "redirect:/section";
    }
    @GetMapping("/lessonList/lesson-delete/{id}")
    public String deleteLessonDTO(@PathVariable("id") Long id) {
      lessonService.delete(id);
        return "redirect:/section";
    }

}
