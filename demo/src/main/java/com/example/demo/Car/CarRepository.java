package com.example.demo.Car;

import com.example.demo.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    @Query("SELECT a FROM Car a WHERE a.model = ?1")
    Optional<Car> findCarByModel(String model);

    List<Car> findCarByNumberOfSeats(Integer numberOfSeats);
    List<Car> findCarByBodyType(String bodyType);
    List<Car> findCarByFuelType(String fuelType);
    List<Car> findCarByBodyPaint(String bodyPaint);
    List<Car> findCarByStatus(String status);
    List<Car> findCarByTaxHorsepower(Integer taxHorsepower);
    List<Car> findCarByTransmissionType(String transmissionType);
    Optional<Car> findById(Long id);

}