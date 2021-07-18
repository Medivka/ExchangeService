package by.sacuta.ExchangeService.mvcController;

import by.sacuta.ExchangeService.model.dto.CourseDTO;
import by.sacuta.ExchangeService.model.dto.LessonDTO;
import by.sacuta.ExchangeService.model.dto.ProfileDTO;
import by.sacuta.ExchangeService.model.model.Course;
import by.sacuta.ExchangeService.model.model.Lesson;
import by.sacuta.ExchangeService.model.model.Profile;
import by.sacuta.ExchangeService.service.api.MyModelMapper;
import by.sacuta.ExchangeService.service.api.ProfileService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.LinkedList;
import java.util.List;

@Controller
public class ProfileController {
    private final MyModelMapper myModelMapper;
    private final ProfileService profileService;

    public ProfileController(MyModelMapper myModelMapper, ProfileService clientsFacade) {
        this.myModelMapper = myModelMapper;
        this.profileService = clientsFacade;
    }

    @GetMapping("/myProfile")
    public String findAll(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        ProfileDTO profileDTO = myModelMapper.mapToProfileDTO(profileService.findByUsername(username));
        model.addAttribute("profileDTO", profileDTO);
        return "myProfile";
    }

    @PostMapping("/myProfile")
    public String updateProfile(ProfileDTO profileDTO) {
        profileService.update(myModelMapper.mapToProfile(profileDTO));
        return "redirect:/section";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        final List<ProfileDTO> profileDTOS = new LinkedList<>();
        for (Profile c : profileService.getAll()
        ) {
            profileDTOS.add(myModelMapper.mapToProfileDTO(c));
        }
        model.addAttribute("profileDTOS", profileDTOS);
        return "admin";
    }

    @GetMapping("/client-update/{id}")
    public String updateClientDTO(@PathVariable("id") Long id, Model model) {
        ProfileDTO profileDTO = myModelMapper.mapToProfileDTO(profileService.findByID(id));
        model.addAttribute("profileDTO", profileDTO);
        return "/client-update";
    }

    @PostMapping("/client-update")
    public String updateClientsDTO(ProfileDTO profileDTO) {
        profileService.update(myModelMapper.mapToProfile(profileDTO));
        return "redirect:/admin";
    }

    @GetMapping("/myActualLesson")
    public String myActualLesson(Model model) {
        final List<LessonDTO> lessonsDTOs = new LinkedList<>();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        for (Lesson le : profileService.getActualLesson(username)
        ) {
            lessonsDTOs.add(myModelMapper.mapToLessonDTO(le));
        }
        model.addAttribute("lessonsDTOs", lessonsDTOs);
        return "myActualLesson";
    }

    @GetMapping("/myCourse")
    public String myCourse(Model model) {
        final List<CourseDTO> courseDTOList = new LinkedList<>();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        for (Course co : profileService.getAllMyCourse(username)
        ) {
            courseDTOList.add(myModelMapper.mapToCourseDTO(co));
        }
        model.addAttribute("courseDTOList", courseDTOList);
        return "myCourse";
    }

    @GetMapping("client-delete/{id}")
    public String deleteClient(@PathVariable("id") Long id) {
        profileService.delete(id);
        return "redirect:/admin";
    }
}
