package com.adbms.app.controllers;

import com.adbms.app.models.shared.Address;
import com.adbms.app.models.Inventory.Inventory;
import com.adbms.app.repositories.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8080")
@Controller
@RequestMapping(value = "/inventories")
public class InventoryController {

    private final InventoryRepository inventoryRepository;

    @Autowired
    public InventoryController(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @GetMapping()
    public String getAllInventorys(Model model) {
        model.addAttribute("inventories", inventoryRepository.findAll());
        return "inventory/inventories";
    }

//    @GetMapping("/{id}")
//    public String getInventory(Model model, @PathVariable("id") String id) {
//        model.addAttribute("inventory", inventoryRepository.findById(id));
//        return "inventory/view-inventory";
//    }


    @GetMapping("/add-inventory")
    public String showAddForm(Inventory inventory) {
        return "inventory/add-inventory";
    }

    @PostMapping("add")
    public String addInventory(Inventory inventory, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "inventory/add-inventory";
        }
        //TODO : err
        inventoryRepository.save(inventory);
        return "redirect:";
    }

    @GetMapping("edit/{id}")
    public String showUpdateForm(@PathVariable("id") String id, Model model) {
        Optional<Inventory> inventory;
        if (inventoryRepository.existsById(id)) {
            inventory = inventoryRepository.findById(id);
        } else {
            throw new IllegalArgumentException("Invalid inventory Id:" + id);
        }
        model.addAttribute("inventory", inventory.get());
        return "inventory/update-inventory";
    }

    @PostMapping("update/{id}")
    public String updateInventory(@PathVariable("id") String id, @Valid Inventory inventory, BindingResult result,
                                 Model model) {
        if (result.hasErrors()) {
            inventory.setId(id);
            return "inventory/update-inventory";
        }

        inventoryRepository.save(inventory);
        model.addAttribute("inventories", inventoryRepository.findAll());
        return "inventory/inventories";
    }

    @GetMapping("delete/{id}")
    public String deleteInventory(@PathVariable("id") String id, Model model) {
        Optional<Inventory> inventory;
        if (inventoryRepository.existsById(id)) {
            inventory = inventoryRepository.findById(id);
        } else {
            throw new IllegalArgumentException("Invalid inventory Id:" + id);
        }

        inventoryRepository.delete(inventory.get());

        model.addAttribute("inventories", inventoryRepository.findAll());
        return "inventory/inventories";
    }

}
