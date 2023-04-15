package com.shop.shmakova.controllers;

import com.shop.shmakova.models.Product;
import com.shop.shmakova.models.enums.TypeOfProducts;
import com.shop.shmakova.services.ProductService;
import com.shop.shmakova.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;


/**
 * @author sh1chiro 16.03.2023
 */
@Controller
@RequiredArgsConstructor
public class MainController {
    private final UserService userService;
    private final ProductService productService;

    @GetMapping("/")
    public String main(@RequestParam(value = "type", required = false) TypeOfProducts typeOfProducts, Model model,
                       Principal principal){
        model.addAttribute("allTypes", Arrays.asList(TypeOfProducts.values()));
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        if(typeOfProducts == TypeOfProducts.ALL || typeOfProducts == null){
            List<Product> products = productService.allProducts();
            products.sort(Comparator.comparing(Product::getDateOfCreated).reversed());
            model.addAttribute("products", products);
            model.addAttribute("selectedType", TypeOfProducts.ALL);
        }
        else{
            List<Product> products = productService.findByType(typeOfProducts);
            products.sort(Comparator.comparing(Product::getDateOfCreated).reversed());
            model.addAttribute("products", products);
            model.addAttribute("selectedType", typeOfProducts);
        }
        return "index";
    }

    @PostMapping("/")
    public String typeOfProduct(@RequestParam(value = "type") TypeOfProducts typeOfProducts){
        System.out.println(typeOfProducts);
        return ("redirect:/?type="+typeOfProducts);
    }
}
