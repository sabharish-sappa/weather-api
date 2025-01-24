package com.vonage.weatherapi.services.userService;

import com.vonage.weatherapi.models.users.User;
import com.vonage.weatherapi.repositories.UserRepo;
import jakarta.persistence.EntityNotFoundException;
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

    public User updateUser(Long id,User updatedUser){

        User userToBeUpdated = userRepo.findById(id).orElseThrow(()->new EntityNotFoundException("No user found with the entered id - "+id+" Try again with different id"));

        if(updatedUser.getName()!=null)
            userToBeUpdated.setName(updatedUser.getName());

        if(updatedUser.getEmail()!=null)
            userToBeUpdated.setEmail(updatedUser.getEmail());

        if(updatedUser.getMobileNumber()!=null)
            userToBeUpdated.setMobileNumber(updatedUser.getMobileNumber());

        return userRepo.save(updatedUser);
    }


    public void deleteUser(Long id){
        userRepo.deleteById(id);
    }
}
