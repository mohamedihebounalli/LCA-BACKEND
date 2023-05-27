package com.example.demo.Sale;

import com.example.demo.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface SaleRepository extends JpaRepository<Sale, Long>{
    @Query("SELECT a FROM Sale a WHERE a.manufacturer = ?1")
    Optional<Sale> findSaleByManufacturer(String manufacturer);
}