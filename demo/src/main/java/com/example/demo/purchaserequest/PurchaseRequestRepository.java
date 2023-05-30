package com.example.demo.purchaserequest;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface PurchaseRequestRepository extends JpaRepository<PurchaseRequest, Long> {
    @Query("SELECT a FROM PurchaseRequest a WHERE a.ownerCIN = ?1")
    Optional<PurchaseRequest> findPurchaseRequestByOwnerCIN(String ownerCIN);


}