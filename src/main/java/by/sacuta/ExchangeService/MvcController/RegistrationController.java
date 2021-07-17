package by.sacuta.ExchangeService.MvcController;

import by.sacuta.ExchangeService.config.MyCustomUserDetailsService;
import by.sacuta.ExchangeService.model.dto.ProfileDTO;
import by.sacuta.ExchangeService.model.model.Profile;
import by.sacuta.ExchangeService.service.api.MyModelMapper;
import by.sacuta.ExchangeService.service.api.ProfileService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.LinkedList;
import java.util.List;

@Controller
public class RegistrationController {

    private final ProfileService profileService;
    private final MyModelMapper myModelMapper;
    private final MyCustomUserDetailsService myCustomUserDetailsService;

    public RegistrationController(ProfileService profileService, MyModelMapper myModelMapper, MyCustomUserDetailsService myCustomUserDetailsService) {
        this.profileService = profileService;
        this.myModelMapper = myModelMapper;
        this.myCustomUserDetailsService = myCustomUserDetailsService;
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new Profile());
        return "registration";
    }

    @GetMapping("/login")
    public String findAll(Model model) {

        List<ProfileDTO> profileDTOS = new LinkedList<>();
        for (Profile cl : profileService.getAll()
        ) {
            profileDTOS.add(myModelMapper.mapToProfileDTO(cl));
        }
        model.addAttribute("profileDTOS", profileDTOS);
        return "login";
    }


    @PostMapping("/registration")
    public String addUser(@ModelAttribute("userForm") ProfileDTO profileDTO) {
        myCustomUserDetailsService.saveUser(myModelMapper.mapToProfile(profileDTO));
        return "redirect:/main";
    }


    @RequestMapping(value = "/myusername", method = RequestMethod.GET)
    @ResponseBody
    public User getCurrentUser(Principal principal) {
        return ((User) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal());
    }
}
