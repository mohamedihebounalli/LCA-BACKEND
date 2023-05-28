package com.example.demo.Car;

import com.example.demo.account.Account;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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






    @GetMapping("/transmission-type/{transmissionType}")
    public List<Car> getCarByTransmissionType(@PathVariable("transmissionType") String transmissionType) throws IllegalAccessException {
        return carService.getCarByTransmissionType(transmissionType);
    }

    @GetMapping("/tax-horse-power/{taxHorsepower}")
    public List<Car> getCarByTaxHorseower(@PathVariable("taxHorsepower") Integer taxHorsepower) throws IllegalAccessException {
        return carService.getCarByTaxHorsepower(taxHorsepower);
    }


    @GetMapping("/number-of-seets/{numberOfSeats}")
    public List<Car> getCarByNumberOfSeats(@PathVariable("numberOfSeats") Integer numberOfSeats) throws IllegalAccessException {
        return carService.getCarByNumberOfSeats(numberOfSeats);
    }


    @GetMapping("/body-type/{bodyType}")
    public List<Car> getCarByBodyType(@PathVariable("bodyType") String bodyType) throws IllegalAccessException {
        return carService.getCarByBodyType(bodyType);
    }

    @GetMapping("/status/{status}")
    public List<Car> getCarListByStatus(@PathVariable("status") String status) throws IllegalAccessException {
        return carService.getCarListByStatus(status);
    }

    @GetMapping("/fuel-type/{fuelType}")
    public List<Car> getCarByFuelType(@PathVariable("fuelType") String fuelType) throws IllegalAccessException {
        return carService.getCarByFuelType(fuelType);
    }

    @GetMapping("/body-paint/{bodyPaint}")
    public List<Car> getCarByBodyPaint(@PathVariable("bodyPaint") String bodyPaint) throws IllegalAccessException {
        return carService.getCarByBodyPaint(bodyPaint);
    }

    @GetMapping("/sort-by/{field}")
    public List <Car> sortCarListByPrice(@PathVariable("field") String field){
        return carService.sortCarListByPrice(field);
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
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String model,
            @RequestParam(required = false) String year,
            @RequestParam(required = false) String bodyPaint,
            @RequestParam(required = false) String bodyType,
            @RequestParam(required = false) String fuelType,
            @RequestParam(required = false) Integer numberOfSeats,
            @RequestParam(required = false) Double price,
            @RequestParam(required = false) Integer numberOfDoors,
            @RequestParam(required = false) String warrantyDuration,
            @RequestParam(required = false) Integer width,
            @RequestParam(required = false) Integer height,
            @RequestParam(required = false) Integer length,
            @RequestParam(required = false) Integer fuelTankCapacity,
            @RequestParam(required = false) Integer maxSpeed,
            @RequestParam(required = false) String acceleration,
            @RequestParam(required = false) String fuelConsumption,
            @RequestParam(required = false) String ownerFullName,
            @RequestParam(required = false) String ownerCIN,
            @RequestParam(required = false) String ownerEmail,
            @RequestParam(required = false) String carDescription,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String phoneNumber){
        carService.updateCar(
                carId, brand, model, year, bodyPaint, bodyType, fuelType, numberOfSeats, price, numberOfDoors,
                warrantyDuration, width, height, length, fuelTankCapacity, maxSpeed, acceleration, fuelConsumption, ownerFullName,  ownerCIN,  ownerEmail,  carDescription,  status,phoneNumber);
    }


    @PostMapping("/upload-car-image/{id}")
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file,@PathVariable("id") Long id) throws IOException {
        String uploadImage = carService.uploadImage(file,id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadImage);
    }

    @GetMapping("/download-car-image/{id}")
    public ResponseEntity<?> downloadImage(@PathVariable Long id){
        byte[] imageData=carService.downloadImage(id);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);
    }

}
