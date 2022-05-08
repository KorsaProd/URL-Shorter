package ru.example.urlshorter.Controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class SignInController {

    @GetMapping("/signIn")
    public String getSignInPage() {
        return "signIn";
    }

}
