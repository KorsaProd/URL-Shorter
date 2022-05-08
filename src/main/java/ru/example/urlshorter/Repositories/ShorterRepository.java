package ru.example.urlshorter.Repositories;

import org.springframework.data.repository.CrudRepository;
import ru.example.urlshorter.Models.Shorter;

import java.util.List;

public interface ShorterRepository extends CrudRepository<Shorter, Long> {

    Shorter findByHash(String hash);
    List<Shorter> findByOwnerId(Integer id);
}
