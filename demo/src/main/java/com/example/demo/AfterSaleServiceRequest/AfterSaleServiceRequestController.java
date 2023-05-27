package com.example.demo.AfterSaleServiceRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/after-sale-service-request")
public class AfterSaleServiceRequestController {

    private final AfterSaleServiceRequestService afterSaleServiceRequestService;

    @Autowired
    public AfterSaleServiceRequestController(AfterSaleServiceRequestService afterSaleServiceRequestService) {
        this.afterSaleServiceRequestService = afterSaleServiceRequestService;
    }

    @GetMapping
    public List<AfterSaleServiceRequest> getRequests() {
        return afterSaleServiceRequestService.getRequests();
    }

    @PostMapping
    public void RegisterNewRequest(@RequestBody AfterSaleServiceRequest request) throws IllegalAccessException {
        afterSaleServiceRequestService.addNewRequest(request);
    }

    @DeleteMapping(path ="{requestId}" )
    public void deleteRequest(@PathVariable("requestId") Long requestId) throws IllegalAccessException {
        afterSaleServiceRequestService.deleteRequest(requestId);
    }


    @PutMapping(path="{requestId}")
    public void updateRequest(
            @PathVariable("requestId") Long requestId,
            @RequestParam(required = false) String CIN,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String manufacturer,
            @RequestParam(required = false) String registrationNumber,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) String status){
        afterSaleServiceRequestService.updateRequest(requestId, CIN,firstName, lastName,  manufacturer,  registrationNumber,  description,status );
    }

}
