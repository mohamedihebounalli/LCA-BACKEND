package com.example.demo.Sale;


import com.example.demo.account.Account;
import com.example.demo.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/sale")
public class SaleController {
    private final SaleService saleService;

    @Autowired
    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @GetMapping
    public List<Sale> getSales() {
        return saleService.getSales();
    }

    @PostMapping
    public void RegisterNewSale(@RequestBody Sale sale) throws IllegalAccessException {
        saleService.addNewSale(sale);
    }
    @DeleteMapping(path ="{saleId}" )
    public void deleteSale(@PathVariable("saleId") Long saleId) throws IllegalAccessException {
        saleService.deleteSale(saleId);
    }

    @PutMapping(path="{saleId}")
    public void updateSale(
            @PathVariable("saleId") Long saleId,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String manufacturer,
            @RequestParam(required = false) String carColor,
            @RequestParam(required = false) String  paymentMethod,
            @RequestParam(required = false) String price){
        saleService.updateSale(saleId,firstName,lastName,manufacturer,carColor,paymentMethod,price);
    }
}
