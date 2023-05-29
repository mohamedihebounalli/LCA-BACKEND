package com.example.demo.Sale;

import com.example.demo.account.Account;
import com.example.demo.account.AccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SaleService {

        private SaleRepository saleRepository;
//    private final List<Account> accounts;

        @Autowired
        public SaleService(SaleRepository saleRepository) {
            this.saleRepository = saleRepository;
//        this.accounts = accounts;
        }
    public List<Sale> getSales() {
        return saleRepository.findAll();
    }


    public ResponseEntity<?> addNewSale(Sale sale)   {
//        Optional<Sale> saleOptional = saleRepository.findSaleByManufacturer(sale.getManufacturer());
//        if(saleOptional.isPresent()){
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Manufactutrer taken");
//        }
        saleRepository.save(sale);
        return ResponseEntity.ok("Vente enregistré avec succès");

    }

    public ResponseEntity<?> deleteSale(Long saleId){
        boolean exists = saleRepository.existsById(saleId);
        if(!exists){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Le vente n'existe pas");
        }
        saleRepository.deleteById(saleId);
        return ResponseEntity.ok("Le vente supprimé avec succès");
    }

    @Transactional
    public ResponseEntity<?> updateSale(Long saleId, String firstName, String lastName, String manufacturer,
                                        String carColor, String  paymentMethod, String price, String ownerCIN) {
        Optional<Sale> saleOptional = saleRepository.findById(saleId);

        Sale sale;
        if(saleOptional.isPresent()) {
            sale = saleOptional.get();
        }else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("la vente n'existe pas");
        }


        if(firstName != null && firstName.length() > 0 && !Objects.equals(sale.getFirstName(),firstName)){
            sale.setFirstName(firstName);
        }
        if(lastName != null && lastName.length() > 0 && !Objects.equals(sale.getLastName(),lastName)){
            sale.setLastName(lastName);
        }

        if(paymentMethod != null && paymentMethod.length() > 0 && !Objects.equals(sale.getPaymentMethod(),paymentMethod)){
            sale.setPaymentMethod(paymentMethod);
        }

        if(price != null && price.length() > 0 && !Objects.equals(sale.getPrice(),price)){
            sale.setPrice(price);
        }
        if(carColor != null && carColor.length() > 0 && !Objects.equals(sale.getCarColor(),carColor)){
            sale.setCarColor(carColor);
        }
        if(ownerCIN != null && ownerCIN.length() > 0 && !Objects.equals(sale.getOwnerCIN(),ownerCIN)){
            sale.setOwnerCIN(ownerCIN);
        }

        if(manufacturer != null && manufacturer.length() > 0 && !Objects.equals(sale.getManufacturer(),manufacturer)){
            Optional<Sale> saleOptional1 = saleRepository.findSaleByManufacturer(manufacturer);
            if(saleOptional1.isPresent()){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("fabricant déjà pris");
            }
            sale.setManufacturer(manufacturer);
        }
        return ResponseEntity.ok("La vente a été modifié avec succès");

    }

    }
