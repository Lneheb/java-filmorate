package ru.yandex.practicum.filmorate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.FilmStorage;
import ru.yandex.practicum.filmorate.storage.UserStorage;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FilmService {
    private final FilmStorage filmStorage;

    private final UserStorage userStorage;

    public List<Film> getFilms() {
        return filmStorage.getFilms();
    }

    public Film createFilm(Film film) {
        film.setLikes(new HashSet<>());
        filmStorage.createFilm(film);
        return film;
    }
    public Film updateFilm(Film film) {
        filmStorage.updateFilm(film);
        return film;
    }
    public Film getFilm(Integer id) {
        return filmStorage.getFilm(id);
    }
    public void putLike(Integer id, Integer userId) {
        if (!userStorage.containsUserId(userId)){
            throw new NotFoundException("User does not exist");
        }
        filmStorage.getFilm(id).getLikes().add(userId);
    }
    public void deleteLike(Integer id, Integer userId) {
        if (!userStorage.containsUserId(userId)){
            throw new NotFoundException("User does not exist");
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
