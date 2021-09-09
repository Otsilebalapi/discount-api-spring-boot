package com.example.discountApp.Discount.service.Impl;

import com.example.discountApp.Discount.service.IDiscountService;

import com.example.discountApp.User.repository.IUserRepository;

import com.example.discountApp.User.model.*;

import org.springframework.stereotype.Service;


@Service
public class DiscountServiceImpl implements IDiscountService{

    private final IUserRepository iUserRepository;
    double discountedBill =  0; 

    public DiscountServiceImpl(IUserRepository iUserRepository){

        this.iUserRepository = iUserRepository;
    }
    
    @Override
    public double calculateDiscount(String username, int bill)
    {
        User customer = iUserRepository.findUserByUsername(username);
        String userType = customer.getUsertype().toString();
        int numOfyearsAsUser = customer.getNumOfyearsAsUser();

        if(userType == "Employee")
        {
            discountedBill = bill - (bill*0.3);
        } else if(userType == "Affiliate")
        {
            discountedBill = bill - (bill*0.1);
        } else if(userType == "Customer" && numOfyearsAsUser > 2)
        {
            System.out.println("Numof Years>>>>>>>>>>>>>>>>>>>>>>>>"+numOfyearsAsUser);
            discountedBill = bill - (bill*0.05);
        }

        if(discountedBill <= 0)
        {
            discountedBill =  bill*1.0;
        }

        return discountedBill;
    }

    
}
