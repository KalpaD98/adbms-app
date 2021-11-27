package com.adbms.app.controllers;

import com.adbms.app.models.OrderItems.OrderItems;
import com.adbms.app.repositories.OrderItemsRepository;
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

    @Autowired
    public OrderItemsController(OrderItemsRepository orderItemsRepository) {
        this.orderItemsRepository = orderItemsRepository;
    }

    @GetMapping()
    public String getAllOrderItems(Model model) {
        model.addAttribute("orderItems", orderItemsRepository.findAll());
        return "orderItem/orderItems";
    }

    @GetMapping("/{id}")
    public String getOrderItem(Model model, @PathVariable("id") String id) {
        model.addAttribute("orderItem", orderItemsRepository.findById(id));
        return "orderItem/view-orderItem";
    }

    @GetMapping("/add-orderItem")
    public String showAddForm(OrderItems orderItem) {
        return "orderItem/add-orderItem";
    }

    @PostMapping("add")
    public String addOrderItem(OrderItems orderItem, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "orderItem/add-orderItem";
        }
        //TODO : err
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
        return "orderItem/update-orderItem";
    }

    @PostMapping("update/{id}")
    public String updateOrderItem(@PathVariable("id") String id, @Valid OrderItems orderItem, BindingResult result,
                                 Model model) {
        if (result.hasErrors()) {
            orderItem.setId(id);
            return "orderItem/update-orderItem";
        }

        orderItemsRepository.save(orderItem);
        model.addAttribute("orderItems", orderItemsRepository.findAll());
        return "orderItem/orderItems";
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
        return "orderItem/orderItems";
    }
}
