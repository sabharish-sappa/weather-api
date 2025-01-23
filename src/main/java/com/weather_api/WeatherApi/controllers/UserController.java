package com.weather_api.WeatherApi.controllers;

import com.weather_api.WeatherApi.models.alerts.Location;
import com.weather_api.WeatherApi.models.users.User;
import com.weather_api.WeatherApi.services.userService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
