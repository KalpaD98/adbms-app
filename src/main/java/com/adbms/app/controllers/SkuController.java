package com.adbms.app.controllers;

import com.adbms.app.models.Sku.Sku;
import com.adbms.app.repositories.SkuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8080")
@Controller
@RequestMapping(value = "/skus")
public class SkuController {
//    private final SkuService skuService;
//
//    public SkusController(SkuService skuService) {
//        this.skuService = skuService;
//    }
//
//    @GetMapping()
//    public String getAllSkus(Model model) {
//        model.addAttribute("skus", skuService.getAllSku());
//        return "sku/skus";
//}

    private final SkuRepository skuRepository;
    @Autowired
    public SkuController(SkuRepository skuRepository) {
        this.skuRepository = skuRepository;
    }

    //Get all SKU documents
    @GetMapping()
    public String getAllSkus(Model model) {
        model.addAttribute("skus", skuRepository.findAll());
        return "sku/skusList";
    }


    @GetMapping("/add-sku")
    public String showAddForm(Model model) {
        model.addAttribute("skuForm", new Sku());
        return "sku/add-sku";
    }

    //Add new Sku
    @PostMapping("add")
    public String addSku(@ModelAttribute("skuForm") Sku sku, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "sku/add-sku";
        }
        skuRepository.save(sku);
        return "redirect:";
    }

    //retrieve perticular document
    @GetMapping("edit/{id}")
    public String showUpdateSkuForm(@PathVariable("id") String id, Model model) {
        Optional<Sku> sku;
        if (skuRepository.existsById(id)) {
            sku = skuRepository.findById(id);
        } else {
            throw new IllegalArgumentException("Invalid sku Id:" + id);
        }
        model.addAttribute("sku", sku.get());
        return "sku/update-sku";
    }

    //Update method
    @PostMapping("update/{id}")
    public String updateSku(@PathVariable("id") String id, @Valid Sku sku, BindingResult result,
                            Model model) {
        if (result.hasErrors()) {
            sku.setId(id);
            return "sku/update-sku";
        }

        skuRepository.save(sku);
        model.addAttribute("skus", skuRepository.findAll());
        return "sku/skusList";
    }

    //Delete Method
    @GetMapping("delete/{id}")
    public String deleteSku(@PathVariable("id") String id, Model model) {
        Optional<Sku> sku;
        if (skuRepository.existsById(id)) {
            sku = skuRepository.findById(id);
        } else {
            throw new IllegalArgumentException("Invalid sku Id:" + id);

        }
        skuRepository.delete(sku.get());
        model.addAttribute("skus", skuRepository.findAll());
        return "sku/skusList";
    }

}
