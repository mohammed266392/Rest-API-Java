package com.example.restfulwebservices.user;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Component
public class UserDaoService {

    private static List<User> users = new ArrayList<>();
    private static int indexUser = 0;

    static{
        users.add(new User(++indexUser,"Mohammed", LocalDate.now().minusYears(30)));
        users.add(new User(++indexUser,"Mohammed", LocalDate.now().minusYears(10)));
        users.add(new User(++indexUser,"Mohammed", LocalDate.now().minusYears(15)));
    }

    public List<User> findAll(){
        return users;
    }

    public User findById(int id){
        Predicate<? super User> predicate = user -> user.getId().equals(id);

        Optional<User> user = users.stream().filter(predicate).findFirst();
        return  user.isEmpty() ?  null : user.get();
    }

    public User save(User user){
        user.setId(++indexUser);
        users.add(user);
        return user;
    }

    public void remove(Integer userId){
        Predicate<User> predicate = user1 -> user1.getId().equals(userId);
        users.removeIf(predicate);
    }
}
