package com.shop.shmakova.controllers;

import com.shop.shmakova.models.*;
import com.shop.shmakova.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.Comparator;
import java.util.List;

/**
 * @author sh1chiro 14.04.2023
 */
@Controller
@RequestMapping("/master-classes")
@RequiredArgsConstructor
public class MasterClassController {
    private final MasterClassService masterClassService;
    private final UserService userService;
    private final MasterClassImageService imageService;
    private final MasterClassOrderService orderService;
    private final MasterClassLogService masterClassLogService;

    @GetMapping("")
    public String main(Model model, Principal principal){
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        List<MasterClass> masterClasses = masterClassService.allMasterClasses();
        masterClasses.sort(Comparator.comparing(MasterClass::getDateOfCreated).reversed());
        model.addAttribute("masterClasses", masterClasses);
        return "mk";
    }
    @GetMapping("/{id}")
    public String masterClass(@PathVariable Long id, Model model, Principal principal){
        MasterClass masterClass = masterClassService.getMasterClassById(id);
        model.addAttribute("masterClass", masterClass);
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "masterClass";
    }
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/create")
    public String createMasterClassTemplate(Model model){
        return "masterClassCreate";
    }
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/create")
    public String createProduct(@RequestParam("file1") MultipartFile file1,
                                @RequestParam("file2") MultipartFile file2,
                                @RequestParam("file3") MultipartFile file3,
                                MasterClass masterClass,
                                Principal principal) throws IOException {
        masterClassService.saveMasterClass(masterClass, file1, file2, file3);
        masterClassLogService.log(principal.getName() + " створив МК товар " + masterClass.getTitle());
        return ("redirect:/master-classes");
    }
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/edit/{id}")
    public String editProduct(@PathVariable Long id, Model model){
        model.addAttribute("masterClass", masterClassService.getMasterClassById(id));
        return "masterClassEdit";
    }
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/edit/{id}")
    public String updateProduct(@RequestParam("file1") MultipartFile file1,
                                @RequestParam("file2") MultipartFile file2,
                                @RequestParam("file3") MultipartFile file3,
                                @PathVariable Long id,
                                MasterClass masterClass, Principal principal) throws IOException {
        MasterClass masterClassBeforeUpdate = masterClassService.getMasterClassById(masterClass.getId());

        masterClass.setPreviewImageId(masterClassBeforeUpdate.getPreviewImageId());

        masterClass.setDateOfCreated(masterClassBeforeUpdate.getDateOfCreated());

        List<MasterClassImage> list = imageService.findByMasterClassId(id);
        int size = list.size();
        if(!file1.isEmpty()){
            imageService.remove(list.get(0).getId());
            imageService.add(file1, id);
            List<MasterClassImage> updatedList = imageService.findByMasterClassId(id);
            for (MasterClassImage image : updatedList) {
                if (image.getOriginalFileName().equals(file1.getOriginalFilename())) {
                    masterClass.setPreviewImageId(image.getId());
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
        masterClassLogService.log(principal.getName() + " оновив МК " + masterClassBeforeUpdate.getTitle() +
                "(" + masterClassBeforeUpdate.getId() + ")." + " МК до: " + masterClassBeforeUpdate.toString() +
                " | МК після: " + masterClass.toString());
        masterClassService.updateMasterClass(masterClass);
        return ("redirect:/master-classes/" + id);
    }
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/delete/{id}")
    public String deleteMasterClass(@PathVariable Long id, Principal principal) {
        masterClassLogService.log(principal.getName() + " видалив МК " + masterClassService.getMasterClassById(id).getTitle());
        masterClassService.deleteMasterClass(id);
        return ("redirect:/");
    }

    @GetMapping("/order/{id}")
    public String order(@PathVariable Long id,Model model, Principal principal){
        model.addAttribute("masterClass", masterClassService.getMasterClassById(id));
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "masterClassOrder";
    }

    @PostMapping("/order")
    public String createOrder(MasterClassOrder order){
        orderService.saveOrder(order);
        return ("redirect:/master-classes");
    }
}
