package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.ValidateService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
@RestController
@RequestMapping("/films")
@Slf4j
public class FilmController {
    private final ValidateService validateService = new ValidateService();
    private final HashMap<Integer, Film> films = new HashMap<>();
    private int idCount = 1;

    @GetMapping
    public List<Film> getFilms() {
        return new ArrayList<Film>(films.values());
    }

    @PostMapping
    public Film createFilm(@RequestBody Film film) {
        validateService.validate(film);
        film.setId(idCount);
        idCount++;
        films.put(film.getId(), film);
        log.debug(film.toString());
        return film;
    }

    @PutMapping
    public Film updateFilm(@RequestBody Film film) {
        validateService.validate(film);
        if (!films.containsKey(film.getId())) {
            log.debug("Film does not exist");
            throw new ValidationException();
        }
        films.put(film.getId(), film);
        log.debug(film.toString());
        return film;
    }
}
