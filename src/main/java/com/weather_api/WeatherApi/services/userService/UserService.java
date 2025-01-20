package com.weather_api.WeatherApi.services.userService;

import com.weather_api.WeatherApi.models.users.User;
import com.weather_api.WeatherApi.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    public List<User> getAllUsers(){
        return userRepo.findAll();
    }

    public Optional<User> getUserById(Long id){
        Optional<User> user =  userRepo.findById(id);

        return user;
    }
    public User createUser(User user){
        return userRepo.save(user);
    }

    public User updateUser(User user){
        return userRepo.save(user);
    }


    public void deleteUser(Long id){
        userRepo.deleteById(id);
    }
}
