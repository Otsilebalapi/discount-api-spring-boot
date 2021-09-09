package com.example.discountApp.Discount.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DiscountRequest{

    private String username;
    private int bill;

    public DiscountRequest(String username, int bill)
    {
        this.username = username;
        this.bill = bill;
    }

    public DiscountRequest()
    {

    }
    
}
