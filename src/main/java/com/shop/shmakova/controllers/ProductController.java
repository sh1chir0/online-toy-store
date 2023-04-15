package com.shop.shmakova.controllers;

import com.shop.shmakova.models.*;

import com.shop.shmakova.models.enums.TypeOfProducts;
import com.shop.shmakova.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author sh1chiro 23.03.2023
 */
@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final UserService userService;
    private final ImageService imageService;
    private final OrderService orderService;
    private final ResponseService responseService;
    private final ProductLogService productLogService;


    @GetMapping("/{id}")
    public String product(@PathVariable Long id, Model model, Principal principal){
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        List<Response> responseList = responseService.getByProductId(id);
        responseList.sort(Comparator.comparing(Response::getDateOfCreated).reversed());
        model.addAttribute("responseList", responseList);
        int productStars;
        int stars = 0;
        int amount = 0;
        for (int i = 0; i < responseList.size(); i++) {
            stars += responseList.get(i).getStars();
            amount++;
        }
        productStars = Math.round((float) stars / amount);
        model.addAttribute("stars", productStars);
        return "product";
    }
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/create")
    public String createProductTemplate(Model model){
        model.addAttribute("allTypes", Arrays.asList(TypeOfProducts.values()));
        return "productCreate";
    }
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/create")
    public String createProduct(@RequestParam("file1") MultipartFile file1,
                                @RequestParam("file2") MultipartFile file2,
                                @RequestParam("file3") MultipartFile file3,
                                @RequestParam("type") TypeOfProducts typeOfProducts,
                                Product product,
                                Principal principal) throws IOException {

        product.setType(typeOfProducts);
        productService.saveProduct(product, file1, file2, file3);
        productLogService.log(principal.getName() + " створив новий товар " + product.getTitle());
        return ("redirect:/");
    }
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/edit/{id}")
    public String editProduct(@PathVariable Long id, Model model){
        model.addAttribute("product", productService.getProductById(id));
        model.addAttribute("allTypes", Arrays.asList(TypeOfProducts.values()));
        return "productEdit";
    }
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/edit/{id}")
    public String updateProduct(@RequestParam("file1") MultipartFile file1,
                                @RequestParam("file2") MultipartFile file2,
                                @RequestParam("file3") MultipartFile file3,
                                @PathVariable Long id,
                                @RequestParam("type") TypeOfProducts typeOfProducts,
                                Product product, Principal principal) throws IOException {
        product.setType(typeOfProducts);
        Product productBeforeUpdate = productService.getProductById(product.getId());

        product.setPreviewImageId(productBeforeUpdate.getPreviewImageId());

        product.setDateOfCreated(productBeforeUpdate.getDateOfCreated());

        List<Image> list = imageService.findByProductId(id);
        int size = list.size();
        if(!file1.isEmpty()){
            imageService.remove(list.get(0).getId());
            imageService.add(file1, id);
            List<Image> updatedList = imageService.findByProductId(id);
            for (Image image : updatedList) {
                if (image.getOriginalFileName().equals(file1.getOriginalFilename())) {
                    product.setPreviewImageId(image.getId());
                }
            }
        }
        if(!file2.isEmpty()){
            if(size > 1){
                imageService.remove(list.get(1).getId());
                imageService.add(file2,id);
            }else{
                imageService.add(file2, id);
            }
        }
        if(!file3.isEmpty()){
            if(size > 2){
                imageService.remove(list.get(2).getId());
                imageService.add(file3,id);
            }else{
                imageService.add(file3, id);
            }
        }
        productLogService.log(principal.getName() + " оновив товар " + productBeforeUpdate.getTitle() + "(" + productBeforeUpdate.getId() + ")." +
                " Товар до: " + productBeforeUpdate.toString() + " | товар після: " + product.toString());
        productService.updateProduct(product);
        return ("redirect:/product/" + id);
    }
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id, Principal principal) {
        productLogService.log(principal.getName() + " видалив товар " + productService.getProductById(id).getTitle());
        productService.deleteProduct(id);
        return ("redirect:/");
    }

    @GetMapping("/order/{id}")
    public String order(@PathVariable Long id,Model model, Principal principal){
        model.addAttribute("product", productService.getProductById(id));
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "order";
    }

    @PostMapping("/order")
    public String createOrder(Order order){
        orderService.saveOrder(order);
        return ("redirect:/");
    }
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/{id}/response")
    public String response(@PathVariable Long id, Model model, Principal principal){
        model.addAttribute("product", productService.getProductById(id));
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "responseCreate";
    }
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @PostMapping("/{id}/response")
    public String createResponse(@PathVariable Long id, Response response, Principal principal){
        responseService.saveResponse(response, productService.getProductById(id), userService.getUserByPrincipal(principal));
        return ("redirect:/product/" + id);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/{id_product}/response/{id_response}/delete")
    public String deleteResponse(@PathVariable Long id_product, @PathVariable Long id_response){
        responseService.deleteById(id_response);
        return ("redirect:/product/" + id_product);
    }
}
