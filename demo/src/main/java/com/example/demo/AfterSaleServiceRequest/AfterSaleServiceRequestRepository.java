package com.example.demo.AfterSaleServiceRequest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AfterSaleServiceRequestRepository extends JpaRepository<AfterSaleServiceRequest, Long> {
    @Query("SELECT a FROM AfterSaleServiceRequest a WHERE a.registrationNumber = ?1")
    Optional<AfterSaleServiceRequest> findAfterSaleServiceRequestByRegistrationNumber(String registrationNumber);
}