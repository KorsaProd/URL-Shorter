package ru.example.urlshorter.Services;


import lombok.RequiredArgsConstructor;
import org.apache.commons.text.RandomStringGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.example.urlshorter.Controller.HomePageController;
import ru.example.urlshorter.Forms.ShorterForm;
import ru.example.urlshorter.Models.Shorter;
import ru.example.urlshorter.Models.User;
import ru.example.urlshorter.Repositories.ShorterRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ShorterServiceImpl implements ShorterService {

    Logger logger = LoggerFactory.getLogger(HomePageController.class.getSimpleName());

    private final ShorterRepository shorterRepository;

    public String generateCode(Integer length) {
        RandomStringGenerator randomStringGenerator = new RandomStringGenerator
                .Builder()
                .filteredBy(ShorterServiceImpl::isLatinLetterOrDigit).build();
        return randomStringGenerator.generate(length);
    }

    private static boolean isLatinLetterOrDigit(int codePoint) {
            return ('a' <= codePoint && codePoint <= 'z')
                    || ('0' <= codePoint && codePoint <= '9');
        }

    @Override
    public void upTransitionCount(Shorter shorter) {
        Integer counter = shorter.getTransitionCounter();
        counter++;
        shorter.setTransitionCounter(counter);
        shorterRepository.save(shorter);
    }

    @Override
    public void addShortLink(ShorterForm form, User user) {

        try {
            Shorter shorter = Shorter.builder()
                    .hash(generateCode(form.getShorterLength()))
                    .originalUrl(form.getOriginalUrl())
                    .createdAt(new Date())
                    .expiresDate(
                            new SimpleDateFormat("yyyy-MM-dd hh:mm")
                                    .parse(form.getExpiresDate().replace('T', ' ')))
                    .transitionCounter(0)
                    .owner(user)
                    .shorterLength(form.getShorterLength())
                    .build();
            logger.info("Short link saved: " + shorter);
            shorterRepository.save(shorter);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Shorter> findAllByUser(User user) {
        return shorterRepository.findByOwnerId(user.getId());
    }

    @Override
    public void deleteByHash(String hash) {
        Shorter shorter = shorterRepository.findByHash(hash);
        logger.info("Link has been removed: " + shorter);
        shorterRepository.delete(shorter);
    }


}
