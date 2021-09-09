package com.example.discountApp.Discount.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import io.swagger.annotations.ApiOperation;
//import lombok.RequiredArgsConstructor;

import com.example.discountApp.Discount.model.DiscountRequest;
import com.example.discountApp.Discount.service.IDiscountService;

//@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/discount")
public class DiscountApi {

    @Autowired
    private final IDiscountService iDiscountService;

    public DiscountApi(IDiscountService iDiscountService)
    {
        this.iDiscountService = iDiscountService;
    }


    @PostMapping("/getDiscount")
    public double getDiscount(@RequestBody DiscountRequest discountRequest) throws Exception
    {
        return iDiscountService.calculateDiscount(discountRequest.getUsername(),discountRequest.getBill());
    }

    
}
