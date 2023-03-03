package ru.yandex.practicum.filmorate.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.LocalDate;

public class FilmControllerTest {
    private FilmController filmController = new FilmController();
    @Test
    public void createFilm_throwValidateException_blankName() {
        Film film = new Film(1,"", "desc", LocalDate.of(2000,1,1),100,null);
        Assertions.assertThrows(ValidationException.class, () -> filmController.createFilm(film));
    }
    @Test
    public void createFilm_throwValidateException_negativeDuration() {
        Film film = new Film(1,"name", "desc", LocalDate.of(2000,1,1),0,null);
        Assertions.assertThrows(ValidationException.class, () -> filmController.createFilm(film));
    }
    @Test
    public void createFilm_throwValidateException_invalidDate() {
        Film film = new Film(1,"name", "desc", LocalDate.of(1895,12,27), 100,null);
        Assertions.assertThrows(ValidationException.class, () -> filmController.createFilm(film));
    }
    @Test
    public void updateFilm_throwValidateException_notExist() {
        Film film = new Film(1,"name", "desc", LocalDate.of(2000,1,1),100,null);
        Assertions.assertThrows(ValidationException.class, () -> filmController.updateFilm(film));
    }
    @Test
    public void updateFilm_noException() {
        Film film = new Film(1,"name", "desc", LocalDate.of(2000,1,1),100,null);
        filmController.createFilm(film);
        film.setName("name1");
        filmController.updateFilm(film);
    }
}
