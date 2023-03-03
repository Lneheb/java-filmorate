package ru.yandex.practicum.filmorate.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.LocalDate;
@Slf4j
@Service
public class ValidateFilmService {
    public void validate(Film film) {
        if (film.getName() == null || film.getName().isBlank()) {
            log.debug("Invalid name");
            throw new ValidationException("Invalid name");
        }
        if (film.getDuration() <= 0) {
            log.debug("Invalid duration");
            throw new ValidationException("Invalid duration");
        }
        if (film.getReleaseDate().isBefore(LocalDate.of(1895,12,28))) {
            log.debug("Invalid release date");
            throw new ValidationException("Invalid release date");
        }
        if (film.getDescription().length() > 200) {
            log.debug("Invalid description");
            throw new ValidationException("Invalid description");
        }
    }
}
