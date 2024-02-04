package pl.rest.webservices.restfulwebservices.dao;

import org.springframework.stereotype.Service;
import pl.rest.webservices.restfulwebservices.model.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
public class UserDaoService {

    private static final List<User> users = new ArrayList<>();
    private static int usersCount = 0;

    static {
        users.add(new User(++usersCount, "Adam", LocalDate.now().minusYears(30)));
        users.add(new User(++usersCount, "Eve", LocalDate.now().minusYears(20)));
        users.add(new User(++usersCount, "Jim", LocalDate.now().minusYears(25)));
    }

    public List<User> findAll() {
        return users;
    }

    public User save(User user) {
        user.setId(++usersCount);
        users.add(user);
        return user;
    }

    public User findOne(int id) {
        return users.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void deleteById(int id) {
        Predicate<User> userPredicate = u -> u.getId().equals(id);
        users.removeIf(userPredicate);
    }
}
