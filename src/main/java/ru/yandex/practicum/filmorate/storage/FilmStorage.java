package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.model.Film;

import java.util.List;

public interface FilmStorage {
    List<Film> getFilms();
    void createFilm(Film film);
    void updateFilm(Film film);
    Film getFilm(Integer id);
}
