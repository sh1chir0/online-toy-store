package com.shop.shmakova.controllers;

import com.shop.shmakova.models.CustomerInquiry;
import com.shop.shmakova.services.CustomerInquiryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author sh1chiro 31.03.2023
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/about")
public class CustomerInquiryController {
    private final CustomerInquiryService customerInquiryService;
    @GetMapping("")
    public String about(){
        return "about";
    }
    @PostMapping("")
    public String newInquiry(CustomerInquiry customerInquiry){
        customerInquiryService.saveInquiry(customerInquiry);
        // notification
        return ("redirect:/about");
    }
}
