package ru.yandex.practicum.filmorate.storage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exception.FilmNotFoundException;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
@Slf4j
public class InMemoryFilmStorage implements FilmStorage{
    private final HashMap<Integer, Film> films = new HashMap<>();
    @Override
    public List<Film> getFilms() {
        return new ArrayList<>(films.values());
    }
    @Override
    public void createFilm(Film film) {
        films.put(film.getId(), film);
    }

    @Override
    public void updateFilm(Film newFilm) {
        if (!films.containsKey(newFilm.getId())) {
            log.debug("Film does not exist");
            throw new FilmNotFoundException("Film does not exist");
        }
        Film oldFilm = films.get(newFilm.getId());
        newFilm.setLikes(oldFilm.getLikes());
        films.put(newFilm.getId(), newFilm);
    }

    @Override
    public Film getFilm(Integer id) {
        if (!films.containsKey(id)) {
            log.debug("Film does not exist");
            throw new FilmNotFoundException("Film does not exist");
        }
        return films.get(id);
    }
}
