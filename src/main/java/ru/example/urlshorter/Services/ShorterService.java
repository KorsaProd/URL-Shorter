package ru.example.urlshorter.Services;

import ru.example.urlshorter.Forms.ShorterForm;
import ru.example.urlshorter.Models.Shorter;
import ru.example.urlshorter.Models.User;

import java.util.List;

public interface ShorterService {

    void upTransitionCount(Shorter shorter);
    void addShortLink(ShorterForm form, User user);
    List<Shorter> findAllByUser(User user);
    void deleteByHash(String hash);
}
