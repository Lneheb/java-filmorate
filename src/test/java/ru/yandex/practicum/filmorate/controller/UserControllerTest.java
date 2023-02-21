package ru.yandex.practicum.filmorate.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;

public class UserControllerTest {
    private UserController userController = new UserController();

    @Test
    public void createUserIncorrectEmail() {
        User user = new User(1, "email", "login" ,"name", LocalDate.of(2000,1,1));
        Assertions.assertThrows(ValidationException.class, () -> userController.createUser(user));
    }
    @Test
    public void createUserBlankEmail() {
        User user = new User(1, " ", "login" ,"name", LocalDate.of(2000,1,1));
        Assertions.assertThrows(ValidationException.class, () -> userController.createUser(user));
    }
    @Test
    public void createUserIncorrectLogin() {
        User user = new User(1, "email@e.com", "log in" ,"name", LocalDate.of(2000,1,1));
        Assertions.assertThrows(ValidationException.class, () -> userController.createUser(user));
    }
    @Test
    public void createUserBlankLogin() {
        User user = new User(1, "email@e.com", "" ,"name", LocalDate.of(2000,1,1));
        Assertions.assertThrows(ValidationException.class, () -> userController.createUser(user));
    }
    @Test
    public void createUserIncorrectBirthday() {
        User user = new User(1, "email@e.com", "login" ,"name", LocalDate.of(2100,1,1));
        Assertions.assertThrows(ValidationException.class, () -> userController.createUser(user));
    }
    @Test
    public void createUserNoName() {
        User user = new User(1, "email@e.com", "login" ,null, LocalDate.of(2000,1,1));
        userController.createUser(user);
        Assertions.assertEquals("login", userController.createUser(user).getName());
    }
    @Test
    public void updateUserNotExist() {
        User user = new User(1000, "email@e.com", "login" ,"name", LocalDate.of(2000,1,1));
        Assertions.assertThrows(ValidationException.class, () -> userController.updateUser(user));
    }
}
