package ru.example.urlshorter.Controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.example.urlshorter.Models.Shorter;
import ru.example.urlshorter.Repositories.ShorterRepository;
import ru.example.urlshorter.Services.ShorterService;

import java.util.Date;

@RestController
@RequestMapping
public class ShorterController {

    Logger logger = LoggerFactory.getLogger(ShorterController.class.getSimpleName());

    private final ShorterRepository shorterRepository;
    private final ShorterService shorterService;
    private Integer shorterLength = 5;

    @Autowired
    public ShorterController(final ShorterRepository shorterRepository, ShorterService shorterService) {
        this.shorterRepository = shorterRepository;
        this.shorterService = shorterService;
    }

    @GetMapping(path = "/{hash}")
    public ResponseEntity redirectShorter(@PathVariable("hash") String hash) {
        Shorter shorter = shorterRepository.findByHash(hash);
        if (shorter != null && !shorter.getExpiresDate().before(new Date())) {
            shorterService.upTransitionCount(shorter);
            logger.info(
                    "For link '" + shorter.getHash() +
                    "' TransitionCounter is: " + shorter.getTransitionCounter());
            HttpHeaders headers = new HttpHeaders();
            headers.add("Location", shorter.getOriginalUrl());
            return new ResponseEntity<String>(headers, HttpStatus.FOUND);
        } else {
            logger.info("Link '" + shorter.getHash() + "' was expired");
            shorterService.deleteByHash(shorter.getHash());
            return ResponseEntity.notFound().build();
        }
    }

}
