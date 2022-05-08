package ru.example.urlshorter.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.example.urlshorter.Forms.SignUpForm;
import ru.example.urlshorter.Models.User;
import ru.example.urlshorter.Repositories.UserRepository;

@Component
@RequiredArgsConstructor
public class SignUpServiceImpl implements SignUpService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;


    @Override
    public void signUpUser(SignUpForm form) {
        User user = User.builder()
                .firstName(form.getFirstName())
                .lastName(form.getLastName())
                .email(form.getEmail())
                .role("USER")
                .hashPassword(passwordEncoder.encode(form.getPassword()))
                .build();

        userRepository.save(user);
    }
}