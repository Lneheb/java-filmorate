package ru.yandex.practicum.filmorate.exception;

public class FilmOrUserNotFoundException extends RuntimeException {
    public FilmOrUserNotFoundException(String message) {
        super(message);
    }
}
