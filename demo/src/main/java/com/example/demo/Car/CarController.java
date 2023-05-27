package com.example.demo.Car;

import com.example.demo.account.Account;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/car")
public class CarController {
    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public List<Car> getCars() {
        return carService.getCars();
    }

    @GetMapping("/number-of-seets/{numberOfSeats}")
    public List<Car> getCarByNumberOfSeats(@PathVariable("numberOfSeats") Integer numberOfSeats) throws IllegalAccessException {
        return carService.getCarByNumberOfSeats(numberOfSeats);
    }


    @GetMapping("/body-type/{bodyType}")
    public List<Car> getCarByBodyType(@PathVariable("bodyType") String bodyType) throws IllegalAccessException {
        return carService.getCarByBodyType(bodyType);
    }

    @GetMapping("/fuel-type/{fuelType}")
    public List<Car> getCarByFuelType(@PathVariable("fuelType") String fuelType) throws IllegalAccessException {
        return carService.getCarByFuelType(fuelType);
    }

    @PostMapping
    public void RegisterNewCar(@RequestBody Car car) throws IllegalAccessException {
        carService.addNewCars(car);
    }

    @DeleteMapping(path ="{carId}" )
    public void deleteCar(@PathVariable("carId") Long carId) throws IllegalAccessException {
        carService.deleteCar(carId);
    }

    @PutMapping(path="{carId}")
    public void updateCar(
            @PathVariable("carId") Long carId,
            @RequestBody(required = false) String brand,
            @RequestBody(required = false) String model,
            @RequestBody(required = false) String year,
            @RequestBody(required = false) String bodyPaint,
            @RequestBody(required = false) String bodyType,
            @RequestBody(required = false) String fuelType,
            @RequestBody(required = false) Integer numberOfSeats,
            @RequestBody(required = false) Double price,
            @RequestBody(required = false) Integer numberOfDoors,
            @RequestBody(required = false) String warrantyDuration,
            @RequestBody(required = false) Integer width,
            @RequestBody(required = false) Integer height,
            @RequestBody(required = false) Integer length,
            @RequestBody(required = false) Integer fuelTankCapacity,
            @RequestBody(required = false) Integer maxSpeed,
            @RequestBody(required = false) String acceleration,
            @RequestBody(required = false) String fuelConsumption){
        carService.updateCar(
                carId, brand, model, year, bodyPaint, bodyType, fuelType, numberOfSeats, price, numberOfDoors,
                warrantyDuration, width, height, length, fuelTankCapacity, maxSpeed, acceleration, fuelConsumption
        );
    }
}
