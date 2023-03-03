package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;

import java.util.List;

public interface UserStorage {
    List<User> getUsers();
    void createUser(User user);
    void updateUser(User user);
    User getUser(Integer id);
    boolean containsUserId(Integer id);
}
