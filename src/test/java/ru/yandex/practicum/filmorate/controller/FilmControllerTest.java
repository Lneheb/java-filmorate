package ru.yandex.practicum.filmorate.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.LocalDate;

public class FilmControllerTest {
    private FilmController filmController = new FilmController();
    @Test
    public void createFilmBlankName() {
        Film film = new Film(1,"", "desc", LocalDate.of(2000,1,1),100);
        Assertions.assertThrows(ValidationException.class, () -> filmController.createFilm(film));
    }
    @Test
    public void createFilmNegativeDuration() {
        Film film = new Film(1,"name", "desc", LocalDate.of(2000,1,1),0);
        Assertions.assertThrows(ValidationException.class, () -> filmController.createFilm(film));
    }
    @Test
    public void createFilmInvalidDate() {
        Film film = new Film(1,"name", "desc", LocalDate.of(1895,12,27), 100);
        Assertions.assertThrows(ValidationException.class, () -> filmController.createFilm(film));
    }
    @Test
    public void updateFilmNotExist() {
        Film film = new Film(1,"name", "desc", LocalDate.of(2000,1,1),100);
        Assertions.assertThrows(ValidationException.class, () -> filmController.updateFilm(film));
    }
}
