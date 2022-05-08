package ru.example.urlshorter.Services;


import ru.example.urlshorter.Models.User;

public interface UserService {
    User findUserByEmail(String userEmail);
    User getAuthenticatedUser();
}
