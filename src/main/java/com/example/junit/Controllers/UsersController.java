package com.example.junit.Controllers;

import com.example.junit.Models.User;
import com.example.junit.Services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UsersController {
    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping
    public User getUserById(int id){
        return usersService.getById(id);
    }

    @GetMapping("/all")
    public List<User> getAll(){
        return usersService.getAll();
    }

    @PutMapping
    public User putUser(@RequestBody User user, @RequestParam int id){
        return  usersService.putUser(id, user);
    }

    @PostMapping
    public User saveUser(@ModelAttribute User user){
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(user.getPassword());
        return usersService.saveUser(newUser);
    }

    @DeleteMapping
    public int deleteUser(@RequestParam int id){
        return usersService.deleteUser(id);
    }
}
