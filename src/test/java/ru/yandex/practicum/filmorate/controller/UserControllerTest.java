package ru.yandex.practicum.filmorate.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.exception.UserNotFoundException;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.UserService;
import ru.yandex.practicum.filmorate.service.ValidateUserService;
import ru.yandex.practicum.filmorate.storage.InMemoryUserStorage;

import java.time.LocalDate;

public class UserControllerTest {
    private UserController userController = new UserController(new UserService(new ValidateUserService(), new InMemoryUserStorage()));

    @Test
    public void createUser_throwValidateException_incorrectEmail() {
        User user = new User(1, "email", "login" ,"name", LocalDate.of(2000,1,1),null);
        Assertions.assertThrows(ValidationException.class, () -> userController.createUser(user));
    }
    @Test
    public void createUser_throwValidateException_blankEmail() {
        User user = new User(1, " ", "login" ,"name", LocalDate.of(2000,1,1),null);
        Assertions.assertThrows(ValidationException.class, () -> userController.createUser(user));
    }
    @Test
    public void createUser_throwValidateException_incorrectLogin() {
        User user = new User(1, "email@e.com", "log in" ,"name", LocalDate.of(2000,1,1),null);
        Assertions.assertThrows(ValidationException.class, () -> userController.createUser(user));
    }
    @Test
    public void createUser_throwValidateException_blankLogin() {
        User user = new User(1, "email@e.com", "" ,"name", LocalDate.of(2000,1,1),null);
        Assertions.assertThrows(ValidationException.class, () -> userController.createUser(user));
    }
    @Test
    public void createUser_throwValidateException_incorrectBirthday() {
        User user = new User(1, "email@e.com", "login" ,"name", LocalDate.of(2100,1,1),null);
        Assertions.assertThrows(ValidationException.class, () -> userController.createUser(user));
    }
    @Test
    public void createUser_nameShouldBeEqualLogin() {
        User user = new User(1, "email@e.com", "login" ,null, LocalDate.of(2000,1,1),null);
        userController.createUser(user);
        Assertions.assertEquals("login", userController.createUser(user).getName());
    }
    @Test
    public void updateUser_throwUserNotFoundException_notExist() {
        User user = new User(1000, "email@e.com", "login" ,"name", LocalDate.of(2000,1,1),null);
        Assertions.assertThrows(UserNotFoundException.class, () -> userController.updateUser(user));
    }
    @Test
    public void updateUser_noException() {
        User user = new User(1000, "email@e.com", "login" ,"name", LocalDate.of(2000,1,1),null);
        userController.createUser(user);
        user.setName("name1");
        userController.updateUser(user);
    }
}
