package ru.yandex.practicum.filmorate.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import ru.yandex.practicum.filmorate.exception.UserNotFoundException;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.FilmStorage;
import ru.yandex.practicum.filmorate.storage.UserStorage;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FilmService {

    private ValidateFilmService validateFilmService;

    private FilmStorage filmStorage;

    private UserStorage userStorage;
    @Autowired
    public FilmService(ValidateFilmService validateFilmService, FilmStorage filmStorage, UserStorage userStorage) {
        this.validateFilmService = validateFilmService;
        this.filmStorage = filmStorage;
        this.userStorage = userStorage;
    }

    private int idCount = 1;

    public List<Film> getFilms() {
        return filmStorage.getFilms();
    }

    public Film createFilm(Film film) {
        validateFilmService.validate(film);
        film.setId(idCount);
        idCount++;
        film.setLikes(new HashSet<>());
        filmStorage.createFilm(film);
        return film;
    }
    public Film updateFilm(Film film) {
        validateFilmService.validate(film);
        filmStorage.updateFilm(film);
        return film;
    }
    public Film getFilm(Integer id) {
        return filmStorage.getFilm(id);
    }
    public void putLike(Integer id, Integer userId) {
        if (!userStorage.containsUserId(userId)){
            throw new UserNotFoundException("User does not exist");
        }
        filmStorage.getFilm(id).getLikes().add(userId);
    }
    public void deleteLike(Integer id, Integer userId) {
        if (!userStorage.containsUserId(userId)){
            throw new UserNotFoundException("User does not exist");
        }
        filmStorage.getFilm(id).getLikes().remove(userId);
    }
    public List<Film> getMostPopularFilms(Integer count) {
        return filmStorage.getFilms().stream()
                .sorted((f1,f2) -> {return f2.getLikes().size() - f1.getLikes().size();})
                .limit(count)
                .collect(Collectors.toList());
    }
}
