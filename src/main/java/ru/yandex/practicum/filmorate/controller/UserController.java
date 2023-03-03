package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.UserService;
import ru.yandex.practicum.filmorate.service.ValidateUserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    private UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers() {
        log.debug("GET users");
        return userService.getUsers();
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        log.debug("POST " + user.toString());
        return userService.createUser(user);
    }

    @PutMapping
    public User updateUser(@RequestBody User user) {
        log.debug("PUT " + user.toString());
        return userService.updateUser(user);
    }
    @GetMapping("/{id}")
    public User getUser(@PathVariable Integer id) {
        log.debug("GET user");
        return userService.getUser(id);
    }
    @PutMapping("/{id}/friends/{friendId}")
    public void addFriend(@PathVariable Integer id, @PathVariable Integer friendId) {
        log.debug("PUT addFriend");
        userService.addFriend(id, friendId);
    }
    @DeleteMapping("/{id}/friends/{friendId}")
    public void deleteFriend(@PathVariable Integer id, @PathVariable Integer friendId) {
        log.debug("DELETE friend");
        userService.deleteFriend(id, friendId);
    }
    @GetMapping("/{id}/friends")
    public Set<User> getFriends(@PathVariable Integer id) {
        log.debug("GET friends");
        return userService.getFriends(id);
    }
    @GetMapping("/{id}/friends/common/{otherId}")
    public Set<User> getCommonFriends(@PathVariable Integer id, @PathVariable Integer otherId) {
        log.debug("GET common friends");
        return userService.getCommonFriends(id, otherId);
    }
}
