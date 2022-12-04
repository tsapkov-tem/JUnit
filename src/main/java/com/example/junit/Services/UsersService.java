package com.example.junit.Services;

import com.example.junit.Models.User;
import com.example.junit.Repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {
    private final UsersRepository usersRepository;

    @Autowired
    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public User getById(int id){
        return usersRepository.findById(id).orElse(null);
    }

    public List<User> getAll(){
        return (List<User>) usersRepository.findAll();
    }

    public User putUser(int id, User user){
        User oldUser = usersRepository.findById(id).orElse(null);
        assert oldUser != null;
        oldUser = user;
        usersRepository.save(oldUser);
        return oldUser;
    }

    public int deleteUser(int id){
        usersRepository.deleteById(id);
        return id;
    }

    public User saveUser(User user){
        usersRepository.save(user);
        return user;
    }
}
