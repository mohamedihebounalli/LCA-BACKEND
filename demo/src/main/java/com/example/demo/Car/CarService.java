package com.example.demo.Car;


import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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


    public List<Car> getCarByTaxHorsepower(Integer taxHorsepower) throws IllegalAccessException {
        List<Car> carList = carRepository.findCarByTaxHorsepower(taxHorsepower);
        if(carList.size()==0){
            throw new IllegalAccessException("TaxHorsepower do not exists");
        }
        return carList;
    }
    public List<Car> getCarByTransmissionType(String transmissionType) throws IllegalAccessException {
        List<Car> carList = carRepository.findCarByTransmissionType(transmissionType);
        if(carList.size()==0){
            throw new IllegalAccessException("transmissionType do not exists");
        }
        return carList;
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

    public List<Car> getCarByBodyPaint(String BodyPaint) throws IllegalAccessException {
        List<Car> carList = carRepository.findCarByBodyPaint(BodyPaint);
        if(carList.size()==0){
            throw new IllegalAccessException("BodyPaint do not exists");
        }
        return carList;
    }

    public List<Car> getCarListByStatus(String status) throws IllegalAccessException {
        List<Car> carList = carRepository.findCarByStatus(status);
        if(carList.size()==0){
            throw new IllegalAccessException("Status do not exists");
        }
        return carList;
    }

    public List<Car> getCars() {
        return carRepository.findAll();
    }


    public ResponseEntity<?> addNewCars(Car car) throws IOException {
        if(Objects.equals(car.getStatus(), "USED_CAR")){
            carRepository.save(car);
        }
        else {
            carRepository.save(car);
        }
        return ResponseEntity.ok("Voiture enregistré avec succès");

    }



    public ResponseEntity<?> deleteCar(Long carId)  {
        boolean exists = carRepository.existsById(carId);
        if(!exists){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("la voiture n'existe pas");
        }
        carRepository.deleteById(carId);
        return ResponseEntity.ok("la voiture supprimé avec succès");

    }


    @Transactional
    public ResponseEntity<?> updateCar(Long carId, String brand, String model, String year,
                                       String bodyPaint, String bodyType, String fuelType,
                                       Integer numberOfSeats, Double price, Integer numberOfDoors,
                                       String warrantyDuration, Integer width, Integer height, Integer length,
                                       Integer fuelTankCapacity, Integer maxSpeed, String acceleration,
                                       String fuelConsumption, String ownerFullName, String ownerCIN,
                                       String ownerEmail, String carDescription, String status ,
                                       String phoneNumber, MultipartFile file) throws IOException {
        Optional<Car> carOptional = carRepository.findById(carId);
        Car car;
        if(carOptional.isPresent()) {
            car = carOptional.get();
        }else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("le voiture n'existe pas");
        }

        if (file != null && !Objects.equals(car.getCarImageData(), ImageUtils.compressImage(file.getBytes()))) {
            car.setCarImageData(ImageUtils.compressImage(file.getBytes()));
        }

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

        if (ownerFullName != null && ownerFullName.length() > 0 && !Objects.equals(car.getOwnerFullName(), ownerFullName)) {
            car.setOwnerFullName(ownerFullName);
        }

        if (ownerCIN != null && ownerCIN.length() > 0 && !Objects.equals(car.getOwnerCIN(), ownerCIN)) {
            car.setOwnerCIN(ownerCIN);
        }

        if (ownerEmail != null && ownerEmail.length() > 0 && !Objects.equals(car.getOwnerEmail(), ownerEmail)) {
            car.setOwnerEmail(ownerEmail);
        }

        if (carDescription != null && carDescription.length() > 0 && !Objects.equals(car.getCarDescription(), carDescription)) {
            car.setCarDescription(carDescription);
        }

        if (status != null && status.length() > 0 && !Objects.equals(car.getStatus(), status)) {
            car.setStatus(status);
        }
        if (phoneNumber != null && phoneNumber.length() > 0 && !Objects.equals(car.getPhoneNumber(), phoneNumber)) {
            car.setPhoneNumber(phoneNumber);
        }
//        if(model != null && model.length() > 0 && !Objects.equals(car.getModel(),model)){
//            Optional<Car> carOptional1 = carRepository.findCarByModel(model);
//            if(carOptional1.isPresent()){
//                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("modèle déjà pris");
//            }
//            car.setModel(model);
//        }
        return ResponseEntity.ok("Le voiture a été modifié avec succès");

    }

    public List<Car> sortCarListByPrice(String field) {
        return carRepository.findAll(Sort.by(Sort.Direction.ASC,field));
    }

    @Transactional
    public String uploadImage(MultipartFile file,Long carId) throws IOException {
        Car car = carRepository.findById(carId).orElseThrow(()-> new IllegalStateException("car with id "+ carId +" does not exist"));
        car.setCarImageData(ImageUtils.compressImage(file.getBytes()));
        if (car != null) {
            return "file uploaded successfully ";
        }
        return null;
    }


    public byte[] downloadImage(Long id) {
        Optional<Car> carOptional = carRepository.findById(id);
        byte[] images = ImageUtils.decompressImage(carOptional.get().getCarImageData());
        return images;
    }



}
