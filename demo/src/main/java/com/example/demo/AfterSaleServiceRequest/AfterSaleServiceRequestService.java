package com.example.demo.AfterSaleServiceRequest;


import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AfterSaleServiceRequestService {

    private AfterSaleServiceRequestRepository afterSaleServiceRequestRepository;

    @Autowired
    public AfterSaleServiceRequestService(AfterSaleServiceRequestRepository afterSaleServiceRequestRepository) {
        this.afterSaleServiceRequestRepository = afterSaleServiceRequestRepository;
//        this.accounts = accounts;
    }

    public List<AfterSaleServiceRequest> getRequests() {
        return afterSaleServiceRequestRepository.findAll();
    }

    public void addNewRequest(AfterSaleServiceRequest request) throws IllegalAccessException {
        Optional<AfterSaleServiceRequest> requestOptional = afterSaleServiceRequestRepository.findAfterSaleServiceRequestByRegistrationNumber(request.getRegistrationNumber());
        if(requestOptional.isPresent()){
            throw new IllegalAccessException("Registration Number taken");
        }
        afterSaleServiceRequestRepository.save(request);
    }

    public void deleteRequest(Long requestId) throws IllegalAccessException {
        boolean exists = afterSaleServiceRequestRepository.existsById(requestId);
        if(!exists){
            throw new IllegalAccessException("After sale service request with id "+ requestId + " does not exists");
        }
        afterSaleServiceRequestRepository.deleteById(requestId);
    }

    @Transactional
    public void updateRequest(Long requestId, String CIN,String firstName, String lastName, String manufacturer, String registrationNumber, String  description) {
        AfterSaleServiceRequest request = afterSaleServiceRequestRepository.findById(requestId).orElseThrow(()-> new IllegalStateException("Request with id "+ requestId +" does not exist"));


        if(CIN != null && CIN.length() > 0 && !Objects.equals(request.getCIN(),CIN)){
            request.setCIN(CIN);
        }
        if(firstName != null && firstName.length() > 0 && !Objects.equals(request.getFirstName(),firstName)){
            request.setFirstName(firstName);
        }
        if(lastName != null && lastName.length() > 0 && !Objects.equals(request.getLastName(),lastName)){
            request.setLastName(lastName);
        }
        if(manufacturer != null && manufacturer.length() > 0 && !Objects.equals(request.getManufacturer(),manufacturer)){
            request.setManufacturer(manufacturer);
        }
        if(description != null && description.length() > 0 && !Objects.equals(request.getDescription(),description)){
            request.setDescription(description);
        }
        if(registrationNumber != null && registrationNumber.length() > 0 && !Objects.equals(request.getRegistrationNumber(),registrationNumber)){
            Optional<AfterSaleServiceRequest> requestOptional = afterSaleServiceRequestRepository.findAfterSaleServiceRequestByRegistrationNumber(registrationNumber);
            if(requestOptional.isPresent()){
                throw new IllegalStateException("Registration Number already taken");
            }
            request.setRegistrationNumber(registrationNumber);
        }
    }

    }