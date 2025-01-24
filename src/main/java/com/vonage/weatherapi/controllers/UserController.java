package com.vonage.weatherapi.controllers;

import com.vonage.weatherapi.models.users.User;
import com.vonage.weatherapi.services.userService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    UserService userService;


    @GetMapping("")
    public List<User> getAllUsers(){

        List<User> users = userService.getAllUsers();

        return users;
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        Optional<User> user =  userService.getUserById(id);

        if(user.isPresent())
            return user.get();

        return null;
    }

    @PostMapping("/register")
    public User createUser(@RequestBody User theUser){
        System.out.println(theUser);
        return userService.createUser(theUser);
    }

    @PatchMapping("/update/{id}")
    public User updateUser(@PathVariable("id")Long id, @RequestBody User updatedUser)  {

            return userService.updateUser(id, updatedUser);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }

}
