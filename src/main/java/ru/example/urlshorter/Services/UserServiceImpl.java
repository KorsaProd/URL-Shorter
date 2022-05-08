package ru.example.urlshorter.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import ru.example.urlshorter.Exceptions.UserNotFoundException;
import ru.example.urlshorter.Models.User;
import ru.example.urlshorter.Repositories.UserRepository;

@Component
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User findUserByEmail(String userEmail) {
        return userRepository.findByEmail(userEmail).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public User getAuthenticatedUser() {
        Authentication aUser = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = aUser.getName();
        User user = userRepository.findByEmail(userEmail).orElseThrow(UserNotFoundException::new);
        return user;
    }


}
