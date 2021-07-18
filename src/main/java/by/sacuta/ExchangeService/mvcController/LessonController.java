package by.sacuta.ExchangeService.mvcController;

import by.sacuta.ExchangeService.model.dto.LessonDTO;
import by.sacuta.ExchangeService.model.model.Lesson;
import by.sacuta.ExchangeService.service.api.LessonService;
import by.sacuta.ExchangeService.service.api.MyModelMapper;
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
        Lesson lesson = lessonService.findById(lessonDTO.getId());
        lesson = myModelMapper.mapToLesson(lessonDTO);
        lessonService.update(lesson);
        return "redirect:/section";
    }
}
