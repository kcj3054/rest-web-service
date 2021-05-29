package com.example.restfulwebservice.user;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class UserDaoService {

    private  static int userCount = 3;
    private static List<User> users = new ArrayList<>();

    static {
        users.add(new User(1, "kim", new Date(), "pass1", "960107"));
        users.add(new User(2, "jang1", new Date(),"pass1", "000215"));
        users.add(new User(3, "jang2", new Date(),"pass1",  "989812"));
    }

    public User save(User user) {
        if (user.getID() == null) {
            user.setID(++userCount);
        }
        users.add(user);
        return user;
    }
    public List<User> findAll() {
        return users;
    }

    public User findOne(int id) {
        for (User user : users) {
            if (user.getID() == id) {
                return user;
            }
        }
        return null;
    }

    public User deleteById(int id)  {
        Iterator<User> iterator = users.iterator();

        while (iterator.hasNext()) {
            User user = iterator.next();
            if(user.getID() == id) {
                iterator.remove();
                return user;
            }
        }
        return null;
    }
}
