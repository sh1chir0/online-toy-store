package com.shop.shmakova.controllers;

import com.shop.shmakova.models.CustomerInquiry;
import com.shop.shmakova.models.MasterClassOrder;
import com.shop.shmakova.models.Order;
import com.shop.shmakova.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @author sh1chiro 30.03.2023
 */
@RequiredArgsConstructor
@Controller
@RequestMapping("/admin")
//@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AdminController {
    private final UserService userService;
    private final OrderService orderService;
    private final MasterClassOrderService masterClassOrderService;
    private final CustomerInquiryService customerInquiryService;
    private final ProductLogService productLogService;
    private final UserLogService userLogService;
    private final MasterClassLogService masterClassLogService;
    @GetMapping("")
    public String admin(){
        return "admin";
    }
    @GetMapping("/users")
    public String usersPage(Model model){
        model.addAttribute("userList", userService.allUsers());
        return "users";
    }
    @PostMapping("/users/ban/{id}")
    public String ban(@PathVariable Long id){
        userService.ban(id);
        return ("redirect:/admin/users");
    }
    @PostMapping("/users/change-role/{id}")
    public String changeRole(@PathVariable Long id){
        userService.changeRole(id);
        return ("redirect:/admin/users");
    }

    @GetMapping("/orders")
    public String orders(Model model){
        model.addAttribute("orderList", orderService.all());
        return "orders";
    }
    @PostMapping("/orders/rejected/{id}")
    public String rejected(@PathVariable Long id){
        Order order = orderService.getOrderById(id);
        order.setStatus("Відхилено");
        orderService.updateOrder(order);
        return ("redirect:/admin/orders");
    }
    @PostMapping("/orders/sent/{id}")
    public String sent(@PathVariable Long id){
        Order order = orderService.getOrderById(id);
        order.setStatus("Відправлено");
        orderService.updateOrder(order);
        return ("redirect:/admin/orders");
    }
    @PostMapping("/orders/received/{id}")
    public String received(@PathVariable Long id){
        Order order = orderService.getOrderById(id);
        order.setStatus("Отримано");
        orderService.updateOrder(order);
        return ("redirect:/admin/orders");
    }

    @GetMapping("/master-class-orders")
    public String masterClassOrders(Model model){
        model.addAttribute("orderList", masterClassOrderService.all());
        return "masterClassOrders";
    }
    @PostMapping("/master-class-orders/rejected/{id}")
    public String masterClassRejected(@PathVariable Long id){
        MasterClassOrder order = masterClassOrderService.getOrderById(id);
        order.setStatus("Відхилено");
        masterClassOrderService.updateOrder(order);
        return ("redirect:/admin/masterClassOrders");
    }
    @PostMapping("/master-class-orders/sent/{id}")
    public String masterClassSent(@PathVariable Long id){
        MasterClassOrder order = masterClassOrderService.getOrderById(id);
        order.setStatus("Відправлено");
        masterClassOrderService.updateOrder(order);
        return ("redirect:/admin/masterClassOrders");
    }
    @PostMapping("/master-class-orders/received/{id}")
    public String masterClassReceived(@PathVariable Long id){
        MasterClassOrder order = masterClassOrderService.getOrderById(id);
        order.setStatus("Отримано");
        masterClassOrderService.updateOrder(order);
        return ("redirect:/admin/masterClassOrders");
    }


    @GetMapping("/inquiries")
    public String inquiries(Model model){
        model.addAttribute("inquiries", customerInquiryService.all());
        return "inquiries";
    }
    @PostMapping("/inquiries/change-view/{id}")
    public String changeView(@PathVariable Long id){
        CustomerInquiry customerInquiry = customerInquiryService.getInquiryById(id);
        if(customerInquiry.isView())
            customerInquiry.setView(false);
        else
            customerInquiry.setView(true);
        customerInquiryService.update(customerInquiry);
        return ("redirect:/admin/inquiries");
    }

    @GetMapping("/product-logs")
    public String productLogs(Model model){
        model.addAttribute("logs", productLogService.all());
        return "product_logs";
    }
    @GetMapping("/master-class-logs")
    public String masterClassLogs(Model model){
        model.addAttribute("logs", masterClassLogService.all());
        return "product_logs";
    }
    @GetMapping("/user-logs")
    public String userLogs(Model model){
        model.addAttribute("logs", userLogService.all());
        return "user_logs";
    }
}
