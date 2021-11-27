package com.adbms.app.controllers;

import com.adbms.app.models.OrderItems.OrderItems;
import com.adbms.app.models.Sku.Sku;
import com.adbms.app.repositories.OrderItemsRepository;
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
@RequestMapping(value = "/orderItems")
public class OrderItemsController {

    private final OrderItemsRepository orderItemsRepository;
    private final SkuRepository skuRepository;

    @Autowired
    public OrderItemsController(OrderItemsRepository orderItemsRepository,
                                SkuRepository skuRepository) {
        this.orderItemsRepository = orderItemsRepository;
        this.skuRepository = skuRepository;
    }

    @GetMapping()
    public String getAllOrderItems(Model model) {
        model.addAttribute("orderItems", orderItemsRepository.findAll());
        return "orderItems/orderItemsList";
    }

    @GetMapping("/{id}")
    public String getOrderItem(Model model, @PathVariable("id") String id) {
        model.addAttribute("orderItem", orderItemsRepository.findById(id));
        return "orderItems/orderItemsList";
    }

    @GetMapping("/add-orderItem")
    public String showAddForm(Model model) {
        model.addAttribute("orderItemForm", new OrderItems());
        return "orderItems/add-orderItem";
    }

    @PostMapping("add")
    public String addOrderItem(OrderItems orderItem, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "orderItems/add-orderItem";
        }
        Sku sku = skuRepository.getSkuByName(orderItem.getItemName());
        orderItem.setSku(sku.getSku());
        orderItem.setUnitPrice(sku.getUnitPrice());
        orderItemsRepository.save(orderItem);
        return "redirect:";
    }

    @GetMapping("edit/{id}")
    public String showUpdateForm(@PathVariable("id") String id, Model model) {
        Optional<OrderItems> orderItem;
        if (orderItemsRepository.existsById(id)) {
            orderItem = orderItemsRepository.findById(id);
        } else {
            throw new IllegalArgumentException("Invalid orderItem Id:" + id);
        }
        model.addAttribute("orderItem", orderItem.get());
        return "orderItems/update-orderItem";
    }

    @PostMapping("update/{id}")
    public String updateOrderItem(@PathVariable("id") String id, @Valid OrderItems orderItem, BindingResult result,
                                 Model model) {
        if (result.hasErrors()) {
            orderItem.setId(id);
            return "orderItems/update-orderItem";
        }

        orderItemsRepository.save(orderItem);
        model.addAttribute("orderItems", orderItemsRepository.findAll());
        return "orderItems/orderItemsList";
    }

    @GetMapping("delete/{id}")
    public String deleteOrderItem(@PathVariable("id") String id, Model model) {
        Optional<OrderItems> orderItem;
        if (orderItemsRepository.existsById(id)) {
            orderItem = orderItemsRepository.findById(id);
        } else {
            throw new IllegalArgumentException("Invalid orderItem Id:" + id);
        }

        orderItemsRepository.delete(orderItem.get());

        model.addAttribute("orderItems", orderItemsRepository.findAll());
        return "orderItems/orderItemsList";
    }
}
