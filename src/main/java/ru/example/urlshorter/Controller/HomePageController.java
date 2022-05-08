package ru.example.urlshorter.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.example.urlshorter.Forms.ShorterForm;
import ru.example.urlshorter.Models.Shorter;
import ru.example.urlshorter.Models.User;
import ru.example.urlshorter.Services.ShorterService;
import ru.example.urlshorter.Services.UserService;

import java.util.List;

@Controller
public class HomePageController {

    private final ShorterService shorterService;
    private final UserService userService;

    @Autowired
    public HomePageController(ShorterService shorterService, UserService userService) {
        this.shorterService = shorterService;
        this.userService = userService;
    }

    @GetMapping("/home")
    public String getHomePage(Model model) {
        User user = userService.getAuthenticatedUser();
        List<Shorter> shorterList = shorterService.findAllByUser(user);
        model.addAttribute("shorterLinks", shorterList);
        return "home";
    }

    @GetMapping("/HomePage")
    public String getHomePageWithContent(Model model) {
        User user = userService.getAuthenticatedUser();
        List<Shorter> shorterList = shorterService.findAllByUser(user);
        model.addAttribute("shorterLinks", shorterList);
        return "home";
    }


    @PostMapping("/home")
    public String createShortUrl(ShorterForm form) {
        Authentication aUser = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = aUser.getName();
        User user = userService.findUserByEmail(userEmail);
        shorterService.addShortLink(form, user);
        return "redirect:/HomePage";
    }

    @PostMapping("/home/{hash}/delete")
    public String deleteUrl(@PathVariable ("hash") String hash) {
        shorterService.deleteByHash(hash);
        return "redirect:/HomePage";
    }

}
