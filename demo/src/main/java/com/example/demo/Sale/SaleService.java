package com.example.demo.Sale;

import com.example.demo.account.Account;
import com.example.demo.account.AccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
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


    public void addNewSale(Sale sale) throws IllegalAccessException {
        Optional<Sale> saleOptional = saleRepository.findSaleByManufacturer(sale.getManufacturer());
        if(saleOptional.isPresent()){
            throw new IllegalAccessException("Manufactutrer taken");
        }
        saleRepository.save(sale);
    }

    public void deleteSale(Long saleId) throws IllegalAccessException {
        boolean exists = saleRepository.existsById(saleId);
        if(!exists){
            throw new IllegalAccessException("sale with id "+ saleId + " does not exists");
        }
        saleRepository.deleteById(saleId);
    }

    @Transactional
    public void updateSale(Long saleId, String firstName, String lastName, String manufacturer, String carColor, String  paymentMethod, String price) {
        Sale sale = saleRepository.findById(saleId).orElseThrow(()-> new IllegalStateException("sale with id "+ saleId +" does not exist"));

        if(firstName != null && firstName.length() > 0 && !Objects.equals(sale.getFirstName(),firstName)){
            sale.setFirstName(firstName);
        }
        if(lastName != null && lastName.length() > 0 && !Objects.equals(sale.getLastName(),lastName)){
            sale.setLastName(lastName);
        }
        if(price != null && price.length() > 0 && !Objects.equals(sale.getPrice(),price)){
            sale.setPrice(price);
        }
        if(carColor != null && carColor.length() > 0 && !Objects.equals(sale.getCarColor(),carColor)){
            sale.setCarColor(carColor);
        }
        if(manufacturer != null && manufacturer.length() > 0 && !Objects.equals(sale.getManufacturer(),manufacturer)){
            Optional<Sale> saleOptional = saleRepository.findSaleByManufacturer(manufacturer);
            if(saleOptional.isPresent()){
                throw new IllegalStateException("manufacturer already taken");
            }
            sale.setManufacturer(manufacturer);
        }
    }

    }
