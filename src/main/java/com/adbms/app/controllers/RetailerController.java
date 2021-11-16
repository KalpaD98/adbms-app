package com.adbms.app.controllers;

import com.adbms.app.models.Retailer.Address;
import com.adbms.app.models.Retailer.Retailer;
import com.adbms.app.models.Retailer.ShippingAddress;
import com.adbms.app.repositories.RetailerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8080")
@Controller
@RequestMapping(value = "/retailers")
public class RetailerController {

    private final RetailerRepository retailerRepository;

    @Autowired
    public RetailerController(RetailerRepository retailerRepository) {
        this.retailerRepository = retailerRepository;
    }

    @GetMapping()
    public String getAllRetailers(Model model) {
        model.addAttribute("retailers", retailerRepository.findAll());
        return "retailer/retailers";
    }

    @GetMapping("/{id}")
    public String getRetailer(Model model, @PathVariable("id") String id) {
        model.addAttribute("retailer", retailerRepository.findById(id));
        return "retailer/view-retailer";
    }


    @GetMapping("/add-retailer")
    public String showAddForm(Retailer retailer, Address address, ShippingAddress shippingAddress) {
        return "retailer/add-retailer";
    }

    @PostMapping("add")
    public String addRetailer(Retailer retailer, Address address, ShippingAddress shippingAddress, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "retailer/add-retailer";
        }
        //TODO : err
        retailerRepository.save(retailer);
        return "redirect:";
    }

    @GetMapping("edit/{id}")
    public String showUpdateForm(@PathVariable("id") String id, Model model) {
        Optional<Retailer> retailer;
        if (retailerRepository.existsById(id)) {
            retailer = retailerRepository.findById(id);
        } else {
            throw new IllegalArgumentException("Invalid retailer Id:" + id);
        }
        model.addAttribute("retailer", retailer.get());
        return "retailer/update-retailer";
    }

    @PostMapping("update/{id}")
    public String updateRetailer(@PathVariable("id") String id, @Valid Retailer retailer, BindingResult result,
                                 Model model) {
        if (result.hasErrors()) {
            retailer.setId(id);
            return "retailer/update-retailer";
        }

        retailerRepository.save(retailer);
        model.addAttribute("retailers", retailerRepository.findAll());
        return "retailer/retailers";
    }

    @GetMapping("delete/{id}")
    public String deleteRetailer(@PathVariable("id") String id, Model model) {
        Optional<Retailer> retailer;
        if (retailerRepository.existsById(id)) {
            retailer = retailerRepository.findById(id);
        } else {
            throw new IllegalArgumentException("Invalid retailer Id:" + id);
        }

        retailerRepository.delete(retailer.get());

        model.addAttribute("retailers", retailerRepository.findAll());
        return "retailer/retailers";
    }

}
