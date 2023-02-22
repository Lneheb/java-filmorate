package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.ValidateUserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {
    private final ValidateUserService validateUserService = new ValidateUserService();
    private final HashMap<Integer, User> users = new HashMap<>();
    private int idCount = 1;

    @GetMapping
    public List<User> getUsers() {
        log.debug("GET users");
        return new ArrayList<User>(users.values());
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        validateUserService.validate(user);
        if (user.getName() == null || user.getName().isBlank()) {
            user.setName(user.getLogin());
        }
        user.setId(idCount);
        idCount++;
        users.put(user.getId(), user);
        log.debug("POST " + user.toString());
        return user;
    }

    @PutMapping
    public User updateUser(@RequestBody User user) {
        validateUserService.validate(user);
        if (!users.containsKey(user.getId())) {
            log.debug("User does not exist");
            throw new ValidationException();
        }
        if (user.getName() == null || user.getName().isBlank()) {
            user.setName(user.getLogin());
        }
        users.put(user.getId(), user);
        log.debug("PUT " + user.toString());
        return user;
    }
}
