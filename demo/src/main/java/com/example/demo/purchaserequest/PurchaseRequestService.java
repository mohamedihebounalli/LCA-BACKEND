package com.example.demo.purchaserequest;


import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PurchaseRequestService {
    private PurchaseRequestRepository purchaseRequestRepository;

    @Autowired
    public PurchaseRequestService(PurchaseRequestRepository purchaseRequestRepository) {
        this.purchaseRequestRepository = purchaseRequestRepository;
    }

    public List<PurchaseRequest> getPurchaseRequests() {
        return purchaseRequestRepository.findAll();
    }

    public ResponseEntity<?> addNewPurchaseRequest(PurchaseRequest purchaseRequest)  {
        Optional<PurchaseRequest> purchaseRequestOptional = purchaseRequestRepository.findPurchaseRequestByOwnerCIN(purchaseRequest.getOwnerCIN());
        if(purchaseRequestOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Numéro CIN est déja pris");
        }
        purchaseRequestRepository.save(purchaseRequest);
        return ResponseEntity.ok("Demande d'achat enregistré avec succès");
    }

    public ResponseEntity<?> deletePurchaseRequest(Long purchaseRequestId) {
        boolean exists = purchaseRequestRepository.existsById(purchaseRequestId);
        if(!exists){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Le demande d'achat n'existe pas");
        }
        purchaseRequestRepository.deleteById(purchaseRequestId);
        return ResponseEntity.ok("Demande d'achat supprimé avec succès");

    }


    @Transactional
    public ResponseEntity<?> updatePurchaseRequest(Long purchaserequestId, String ownerCIN, String firstName, String lastName,
                                        String model, String  email, String address, String phoneNumber) {
        Optional<PurchaseRequest> purchaseRequestOptional = purchaseRequestRepository.findById(purchaserequestId);

        PurchaseRequest purchaseRequest;
        if(purchaseRequestOptional.isPresent()) {
            purchaseRequest = purchaseRequestOptional.get();
        }else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("cette demande de vente n'existe pas");
        }

        if(address != null && address.length() > 0 && !Objects.equals(purchaseRequest.getAddress(),address)){
            purchaseRequest.setAddress(address);
        }
        if(firstName != null && firstName.length() > 0 && !Objects.equals(purchaseRequest.getFirstName(),firstName)){
            purchaseRequest.setFirstName(firstName);
        }
        if(model != null && model.length() > 0 && !Objects.equals(purchaseRequest.getModel(),model)){
            purchaseRequest.setModel(model);
        }
        if(email != null && email.length() > 0 && !Objects.equals(purchaseRequest.getEmail(),email)){
            purchaseRequest.setEmail(email);
        }
        if(phoneNumber != null && phoneNumber.length() > 0 && !Objects.equals(purchaseRequest.getPhoneNumber(),phoneNumber)){
            purchaseRequest.setPhoneNumber(phoneNumber);
        }

        if(ownerCIN != null && ownerCIN.length() > 0 && !Objects.equals(purchaseRequest.getOwnerCIN(),ownerCIN)){
            Optional<PurchaseRequest> purchaseRequestOptional1 = purchaseRequestRepository.findPurchaseRequestByOwnerCIN(ownerCIN);
            if(purchaseRequestOptional1.isPresent()){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Utilisateur déjà pris");
            }
            purchaseRequest.setOwnerCIN(ownerCIN);
        }
        return ResponseEntity.ok("La vente a été modifié avec succès");

    }
}
