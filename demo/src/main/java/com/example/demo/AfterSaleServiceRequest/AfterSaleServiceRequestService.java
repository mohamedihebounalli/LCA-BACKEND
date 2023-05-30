package com.example.demo.AfterSaleServiceRequest;


import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    public ResponseEntity<?> addNewRequest(AfterSaleServiceRequest request)  {
        Optional<AfterSaleServiceRequest> requestOptional = afterSaleServiceRequestRepository.findAfterSaleServiceRequestByRegistrationNumber(request.getRegistrationNumber());
        if(requestOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Numéro d'immatriculation est déja pris");
        }
        afterSaleServiceRequestRepository.save(request);
        return ResponseEntity.ok("demande d'achat enregistré avec succès");
    }

    public ResponseEntity<?> deleteRequest(Long requestId) {
        boolean exists = afterSaleServiceRequestRepository.existsById(requestId);
        if(!exists){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Le demande de SAV n'existe pas");
        }
        afterSaleServiceRequestRepository.deleteById(requestId);
        return ResponseEntity.ok("Demande de SAV supprimé avec succès");

    }

    @Transactional
    public ResponseEntity<?> updateRequest(Long requestId, String CIN, String firstName, String lastName, String manufacturer, String registrationNumber, String  description, String status) {
        Optional<AfterSaleServiceRequest> requestOptional = afterSaleServiceRequestRepository.findById(requestId);

        AfterSaleServiceRequest request;
        if(requestOptional.isPresent()) {
            request = requestOptional.get();
        }else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Le demande de service après vente n'existe pas");
        }

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

        if(status != null && status.length() > 0 && !Objects.equals(request.getStatus(),status)){
            request.setStatus(status);
        }

        if(registrationNumber != null && registrationNumber.length() > 0 && !Objects.equals(request.getRegistrationNumber(),registrationNumber)){
            Optional<AfterSaleServiceRequest> requestOptional1 = afterSaleServiceRequestRepository.findAfterSaleServiceRequestByRegistrationNumber(registrationNumber);
            if(requestOptional1.isPresent()){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Numéro d'immatriculation déja existe");
            }
            request.setRegistrationNumber(registrationNumber);
        }
        return ResponseEntity.ok("Demande de service après vente a été modifié avec succès");
    }

    }