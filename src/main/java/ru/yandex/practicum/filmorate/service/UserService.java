package ru.yandex.practicum.filmorate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Indexed;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.FilmStorage;
import ru.yandex.practicum.filmorate.storage.UserStorage;

import java.util.*;

@Service
public class UserService {
    private ValidateUserService validateUserService;
    private UserStorage userStorage;
    @Autowired
    public UserService(ValidateUserService validateUserService, UserStorage userStorage) {
        this.validateUserService = validateUserService;
        this.userStorage = userStorage;
    }

    private int idCount = 1;

    public List<User> getUsers() {
        return userStorage.getUsers();
    }

    public User createUser(User user) {
        validateUserService.validate(user);
        if (user.getName() == null || user.getName().isBlank()) {
            user.setName(user.getLogin());
        }
        user.setId(idCount);
        idCount++;
        user.setFriends(new HashSet<>());
        userStorage.createUser(user);
        return user;
    }
    public User updateUser(User user) {
        validateUserService.validate(user);
        if (user.getName() == null || user.getName().isBlank()) {
            user.setName(user.getLogin());
        }
        userStorage.updateUser(user);
        return user;
    }
    public User getUser(Integer id) {
        return userStorage.getUser(id);
    }
    public void addFriend(Integer id, Integer friendId) {
        User user = userStorage.getUser(id);
        User friend = userStorage.getUser(friendId);
        user.getFriends().add(friendId);
        friend.getFriends().add(id);
    }
    public void deleteFriend(Integer id, Integer friendId) {
        User user = userStorage.getUser(id);
        User friend = userStorage.getUser(friendId);
        user.getFriends().remove(friendId);
        friend.getFriends().remove(id);
    }
    public Set<User> getFriends(Integer id) {
        User user = userStorage.getUser(id);
        Set<User> friends = new TreeSet<>(Comparator.comparingInt(User::getId));
        for (Integer friendId : user.getFriends()) {
            friends.add(userStorage.getUser(friendId));
        }
        return friends;
    }
    public Set<User> getCommonFriends(Integer id, Integer otherId) {
        User user1 = userStorage.getUser(id);
        User user2 = userStorage.getUser(otherId);
        Set<User> user1Friends = getFriends(user1.getId());
        Set<User> user2Friends = getFriends(user2.getId());
        user1Friends.retainAll(user2Friends);
        return user1Friends;
    }
}
