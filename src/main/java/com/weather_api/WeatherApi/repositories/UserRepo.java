package com.weather_api.WeatherApi.repositories;

import com.weather_api.WeatherApi.models.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
}
