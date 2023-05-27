package com.example.demo.Car;


import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CarService {

    private CarRepository  carRepository;
    private CarRepository CarRepository;
//    private final List<Account> accounts;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
//        this.accounts = accounts;
    }

    public List<Car> getCarByNumberOfSeats(Integer numberOfSeats) throws IllegalAccessException {
        List<Car> carList = carRepository.findCarByNumberOfSeats(numberOfSeats);
        if(carList.size()==0){
            throw new IllegalAccessException("Numbers of Seats do not exists");
        }
        return carList;
    }

    public List<Car> getCarByBodyType(String bodyType) throws IllegalAccessException {
        List<Car> carList = carRepository.findCarByBodyType(bodyType);
        if(carList.size()==0){
            throw new IllegalAccessException("BodyType do not exists");
        }
        return carList;
    }

    public List<Car> getCarByFuelType(String fuelType) throws IllegalAccessException {
        List<Car> carList = carRepository.findCarByFuelType(fuelType);
        if(carList.size()==0){
            throw new IllegalAccessException("FuelType do not exists");
        }
        return carList;
    }


    public List<Car> getCars() {
        return carRepository.findAll();
    }

    public void addNewCars(Car car) throws IllegalAccessException {
        Optional<Car> carOptional = carRepository.findCarByModel(car.getModel());
        if(carOptional.isPresent()){
            throw new IllegalAccessException("Model car taken");
        }
        carRepository.save(car);
    }

    public void deleteCar(Long carId) throws IllegalAccessException {
        boolean exists = carRepository.existsById(carId);
        if(!exists){
            throw new IllegalAccessException("car with id "+ carId + " does not exists");
        }
        carRepository.deleteById(carId);
    }


    @Transactional
    public void updateCar(Long carId,String brand,String model,String year,String bodyPaint,String bodyType,String fuelType,Integer numberOfSeats,Double price,Integer numberOfDoors,String warrantyDuration,Integer width,Integer height,Integer length,Integer fuelTankCapacity, Integer maxSpeed,String acceleration,String fuelConsumption) {
        Car car = carRepository.findById(carId).orElseThrow(()-> new IllegalStateException("car with id "+ carId +" does not exist"));
        if (brand != null && brand.length() > 0 && !Objects.equals(car.getBrand(), brand)) {
            car.setBrand(brand);
        }

        if (model != null && model.length() > 0 && !Objects.equals(car.getModel(), model)) {
            car.setModel(model);
        }

        if (year != null && year.length() > 0 && !Objects.equals(car.getYear(), year)) {
            car.setYear(year);
        }

        if (bodyPaint != null && bodyPaint.length() > 0 && !Objects.equals(car.getBodyPaint(), bodyPaint)) {
            car.setBodyPaint(bodyPaint);
        }

        if (bodyType != null && bodyType.length() > 0 && !Objects.equals(car.getBodyType(), bodyType)) {
            car.setBodyType(bodyType);
        }

        if (fuelType != null && fuelType.length() > 0 && !Objects.equals(car.getFuelType(), fuelType)) {
            car.setFuelType(fuelType);
        }

        if (numberOfSeats != null && numberOfSeats > 0 && !Objects.equals(car.getNumberOfSeats(), numberOfSeats)) {
            car.setNumberOfSeats(numberOfSeats);
        }

        if (price != null && price > 0 && !Objects.equals(car.getPrice(), price)) {
            car.setPrice(price);
        }

        if (numberOfDoors != null && numberOfDoors > 0 && !Objects.equals(car.getNumberOfDoors(), numberOfDoors)) {
            car.setNumberOfDoors(numberOfDoors);
        }

        if (warrantyDuration != null && warrantyDuration.length() > 0 && !Objects.equals(car.getWarrantyDuration(), warrantyDuration)) {
            car.setWarrantyDuration(warrantyDuration);
        }

        if (width != null && width > 0 && !Objects.equals(car.getWidth(), width)) {
            car.setWidth(width);
        }

        if (height != null && height > 0 && !Objects.equals(car.getHeight(), height)) {
            car.setHeight(height);
        }

        if (length != null && length > 0 && !Objects.equals(car.getLength(), length)) {
            car.setLength(length);
        }

        if (fuelTankCapacity != null && fuelTankCapacity > 0 && !Objects.equals(car.getFuelTankCapacity(), fuelTankCapacity)) {
            car.setFuelTankCapacity(fuelTankCapacity);
        }

        if (maxSpeed != null && maxSpeed > 0 && !Objects.equals(car.getMaxSpeed(), maxSpeed)) {
            car.setMaxSpeed(maxSpeed);
        }

        if (acceleration != null && acceleration.length() > 0 && !Objects.equals(car.getAcceleration(), acceleration)) {
            car.setAcceleration(acceleration);
        }

        if (fuelConsumption != null && fuelConsumption.length() > 0 && !Objects.equals(car.getFuelConsumption(), fuelConsumption)) {
            car.setFuelConsumption(fuelConsumption);
        }


        if(model != null && model.length() > 0 && !Objects.equals(car.getModel(),model)){
            Optional<Car> carOptional = carRepository.findCarByModel(model);
            if(carOptional.isPresent()){
                throw new IllegalStateException("email already taken");
            }
            car.setModel(model);
        }
    }

}
