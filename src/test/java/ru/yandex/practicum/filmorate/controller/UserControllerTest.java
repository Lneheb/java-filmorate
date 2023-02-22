package ru.yandex.practicum.filmorate.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;

public class UserControllerTest {
    private UserController userController = new UserController();

    @Test
    public void createUser_throwValidateException_incorrectEmail() {
        User user = new User(1, "email", "login" ,"name", LocalDate.of(2000,1,1));
        Assertions.assertThrows(ValidationException.class, () -> userController.createUser(user));
    }
    @Test
    public void createUser_throwValidateException_blankEmail() {
        User user = new User(1, " ", "login" ,"name", LocalDate.of(2000,1,1));
        Assertions.assertThrows(ValidationException.class, () -> userController.createUser(user));
    }
    @Test
    public void createUser_throwValidateException_incorrectLogin() {
        User user = new User(1, "email@e.com", "log in" ,"name", LocalDate.of(2000,1,1));
        Assertions.assertThrows(ValidationException.class, () -> userController.createUser(user));
    }
    @Test
    public void createUser_throwValidateException_blankLogin() {
        User user = new User(1, "email@e.com", "" ,"name", LocalDate.of(2000,1,1));
        Assertions.assertThrows(ValidationException.class, () -> userController.createUser(user));
    }
    @Test
    public void createUser_throwValidateException_incorrectBirthday() {
        User user = new User(1, "email@e.com", "login" ,"name", LocalDate.of(2100,1,1));
        Assertions.assertThrows(ValidationException.class, () -> userController.createUser(user));
    }
    @Test
    public void createUser_nameShouldBeEqualLogin() {
        User user = new User(1, "email@e.com", "login" ,null, LocalDate.of(2000,1,1));
        userController.createUser(user);
        Assertions.assertEquals("login", userController.createUser(user).getName());
    }
    @Test
    public void updateUser_throwValidateException_notExist() {
        User user = new User(1000, "email@e.com", "login" ,"name", LocalDate.of(2000,1,1));
        Assertions.assertThrows(ValidationException.class, () -> userController.updateUser(user));
    }
}
