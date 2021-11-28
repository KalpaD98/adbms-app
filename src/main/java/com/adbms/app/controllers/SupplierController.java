package com.adbms.app.controllers;

import com.adbms.app.models.Retailer.Address;
import com.adbms.app.models.Retailer.Retailer;
import com.adbms.app.models.Retailer.ShippingAddress;
import com.adbms.app.models.Supplier.Supplier;
import com.adbms.app.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8080")
@Controller
@RequestMapping(value = "/suppliers")
public class SupplierController {

    private final SupplierRepository supplierRepository;

    @Autowired
    public SupplierController(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @GetMapping()
    public String getAllSuppliers(Model model) {
        model.addAttribute("suppliers", supplierRepository.findAll());
        return "supplier/suppliers";
    }

    @GetMapping("/{id}")
    public String getSupplier(Model model, @PathVariable("id") String id) {
        model.addAttribute("supplier", supplierRepository.findById(id));
        return "supplier/view-supplier";
    }

    @GetMapping("/add-supplier")
    public String showAddForm(Supplier supplier) {
        return "supplier/add-supplier";
    }
    @PostMapping("add")
    public String addSupplier(Supplier supplier, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "supplier/add-supplier";
        }
        //TODO : err
        supplierRepository.save(supplier);
        return "redirect:";
    }


    @GetMapping("edit/{id}")
    public String showUpdateForm(@PathVariable("id") String id, Model model) {
        Optional<Supplier> supplier;
        if (supplierRepository.existsById(id)) {
            supplier = supplierRepository.findById(id);
        } else {
            throw new IllegalArgumentException("Invalid supplier Id:" + id);
        }
        model.addAttribute("supplier", supplier.get());
        return "supplier/update-supplier";
    }

    @PostMapping("update/{id}")
    public String updateSupplier(@PathVariable("id") String id, @Valid Supplier supplier, BindingResult result,
                                 Model model) {
        if (result.hasErrors()) {
            supplier.setId(id);
            return "supplier/update-supplier";
        }

        supplierRepository.save(supplier);
        model.addAttribute("suppliers", supplierRepository.findAll());
        return "supplier/suppliers";
    }

    @GetMapping("delete/{id}")
    public String deleteSupplier(@PathVariable("id") String id, Model model) {
        Optional<Supplier> supplier;
        if (supplierRepository.existsById(id)) {
            supplier = supplierRepository.findById(id);
        } else {
            throw new IllegalArgumentException("Invalid supplier Id:" + id);
        }

        supplierRepository.delete(supplier.get());

        model.addAttribute("suppliers", supplierRepository.findAll());
        return "supplier/suppliers";
    }
}
