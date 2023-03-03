package ru.yandex.practicum.filmorate.storage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exception.FilmOrUserNotFoundException;
import ru.yandex.practicum.filmorate.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
@Slf4j
public class InMemoryUserStorage implements UserStorage {
    private final HashMap<Integer, User> users = new HashMap<>();
    private int idCount = 1;
    @Override
    public List<User> getUsers() {
        return new ArrayList<>(users.values());
    }
    @Override
    public void createUser(User user) {
        user.setId(idCount);
        idCount++;
        users.put(user.getId(), user);
    }

    @Override
    public void updateUser(User newUser) {
        if (!users.containsKey(newUser.getId())) {
            log.debug("User does not exist");
            throw new FilmOrUserNotFoundException("User does not exist");
        }
        User oldUser = users.get(newUser.getId());
        newUser.setFriends(oldUser.getFriends());
        users.put(newUser.getId(), newUser);
    }

    @Override
    public User getUser(Integer id) {
        if (!users.containsKey(id)) {
            log.debug("User does not exist");
            throw new FilmOrUserNotFoundException("User does not exist");
        }
        return users.get(id);
    }
    @Override
    public boolean containsUserId(Integer id) {
        return users.containsKey(id);
    }
}
