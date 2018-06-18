package service;

import domain.User;
import repository.UserRepository;

import java.util.List;

public class UserService {

    private static UserRepository userRepository;

    public UserService() {
        UserService.userRepository = new UserRepository();
    }


    public User add(Long id, String name) {
        User user = new User(id, name);
        return userRepository.save(user);
    }

    public List findAll() {
        return userRepository.getAll();
    }
}