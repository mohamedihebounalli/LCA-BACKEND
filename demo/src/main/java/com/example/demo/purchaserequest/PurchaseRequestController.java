package com.example.demo.purchaserequest;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/purchaserequest")
public class PurchaseRequestController {

    private final PurchaseRequestService purchaserequestService;

    @Autowired
    public PurchaseRequestController(PurchaseRequestService purchaserequestService) {
        this.purchaserequestService = purchaserequestService;
    }

    @GetMapping
    public List<PurchaseRequest> getPurchaseRequests() {
        return purchaserequestService.getPurchaseRequests();
    }

    @PostMapping
    public ResponseEntity<?> RegisterNewPurchaseRequest(@RequestBody PurchaseRequest purchaseRequest)  {
        return purchaserequestService.addNewPurchaseRequest(purchaseRequest);
    }
    @DeleteMapping(path ="{purchaserequestId}" )
    public ResponseEntity<?> deletePurchaseRequest(@PathVariable("purchaserequestId") Long purchaserequestId) throws IllegalAccessException {
        return purchaserequestService.deletePurchaseRequest(purchaserequestId);
    }

    @PutMapping(path="{purchaserequestId}")
    public ResponseEntity<?> updatePurchaseRequest(
            @PathVariable("purchaserequestId") Long purchaserequestId,
            @RequestParam(required = false) String ownerCIN,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String model,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) String phoneNumber){
        return purchaserequestService.updatePurchaseRequest(purchaserequestId,ownerCIN,firstName,lastName,model,email,address,phoneNumber);

    }

}
