package com.adbms.app.controllers;

import com.adbms.app.repositories.RetailerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        return "retailers";
    }
}
