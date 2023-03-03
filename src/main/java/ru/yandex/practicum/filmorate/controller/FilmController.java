package ru.yandex.practicum.filmorate.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.FilmService;
import ru.yandex.practicum.filmorate.service.ValidateFilmService;
import ru.yandex.practicum.filmorate.service.ValidateUserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
@RestController
@RequestMapping("/films")
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FilmController {
    private final FilmService filmService;
    private final ValidateFilmService validateFilmService;
    @GetMapping
    public List<Film> getFilms() {
        log.debug("GET films");
        return filmService.getFilms();
    }

    @PostMapping
    public Film createFilm(@RequestBody Film film) {
        log.debug("POST " + film.toString());
        validateFilmService.validate(film);
        return filmService.createFilm(film);
    }

    @PutMapping
    public Film updateFilm(@RequestBody Film film) {
        log.debug("PUT " + film.toString());
        validateFilmService.validate(film);
        return filmService.updateFilm(film);
    }
    @GetMapping("/{id}")
    public Film getFilm(@PathVariable Integer id) {
        log.debug("GET film");
        return filmService.getFilm(id);
    }
    @PutMapping("/{id}/like/{userId}")
    public void putLike(@PathVariable Integer id, @PathVariable Integer userId) {
        log.debug("PUT like");
        filmService.putLike(id, userId);
    }
    @DeleteMapping("/{id}/like/{userId}")
    public void deleteLike(@PathVariable Integer id, @PathVariable Integer userId) {
        log.debug("DELETE like");
        filmService.deleteLike(id, userId);
    }
    @GetMapping("/popular")
    public List<Film> getMostPopularFilms(@RequestParam(required = false, defaultValue = "10") String count) {
        log.debug("GET popular films");
        Integer integerCount = Integer.parseInt(count);
        return filmService.getMostPopularFilms(integerCount);
    }
}
