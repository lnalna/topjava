package ru.javawebinar.topjava.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class InMemoryUserRepositoryImpl implements UserRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryUserRepositoryImpl.class);

    private Map<Integer, User> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        save(new User(1,"admin","admin@adminemail.ru","adminpassword", Role.ROLE_ADMIN));
        save(new User(2,"user","user@useremail.ru","userpassword",Role.ROLE_USER));
        save(new User(3,"aaauser","aaauser@useremail.ru","userpassword",Role.ROLE_USER));
        save(new User(4,"bbbuser","bbbuser@useremail.ru","userpassword",Role.ROLE_USER));
    }

    @Override
    public boolean delete(int id) {
        log.info("delete {}", id);

        return repository.remove(id)!=null;
    }

    @Override
    public User save(User user) {
        log.info("save {}", user);

        if(user.isNew())
            user.setId(counter.incrementAndGet());

        return repository.put(user.getId(), user);
    }

    @Override
    public User get(int id) {
        log.info("get {}", id);

        return repository.get(id);
    }

    @Override
    public List<User> getAll() {
        log.info("getAll");

        List<User> listUsers = new ArrayList<>(repository.values());
        listUsers.sort((u1,u2)->u1.getName().compareTo(u2.getName()));


        return listUsers;
    }

    @Override
    public User getByEmail(String email) {
        log.info("getByEmail {}", email);

        return getAll().stream().filter(u -> u.getEmail().equals(email)).findFirst().orElse(null);
    }
}
