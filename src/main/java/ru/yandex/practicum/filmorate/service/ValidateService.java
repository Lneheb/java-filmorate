package ru.yandex.practicum.filmorate.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;
@Slf4j
public class ValidateService {
    public void validate(User user) {
        if (user.getEmail() == null || user.getEmail().isBlank() || !user.getEmail().contains("@")) {
            log.debug("Invalid email");
            throw new ValidationException();
        }
        if (user.getLogin() == null || user.getLogin().isBlank() || user.getLogin().contains(" ")) {
            log.debug("Invalid login");
            throw new ValidationException();
        }
        if (user.getBirthday() == null || user.getBirthday().isAfter(LocalDate.now())) {
            log.debug("Invalid birthday");
            throw new ValidationException();
        }
    }
    public void validate(Film film) {
        if (film.getName() == null || film.getName().isBlank()) {
            log.debug("Invalid name");
            throw new ValidationException();
        }
        if (film.getDuration() <= 0) {
            log.debug("Invalid duration");
            throw new ValidationException();
        }
        if (film.getReleaseDate().isBefore(LocalDate.of(1895,12,28))) {
            log.debug("Invalid release date");
            throw new ValidationException();
        }
        if (film.getDescription().length() > 200) {
            log.debug("Invalid description");
            throw new ValidationException();
        }
    }
}
