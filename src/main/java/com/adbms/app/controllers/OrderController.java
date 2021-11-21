package com.adbms.app.controllers;

import com.adbms.app.enums.OrderStatus;
import com.adbms.app.models.Retailer.Retailer;
import com.adbms.app.models.order.Order;
import com.adbms.app.repositories.OrderRepository;
import com.adbms.app.repositories.RetailerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@RequestMapping(value = "/orders")
@CrossOrigin(origins = "http://localhost:8080")
public class OrderController {

    @Autowired
    private final RetailerRepository retailerRepository;

    @Autowired
    private final OrderRepository orderRepository;

    public OrderController(RetailerRepository retailerRepository, OrderRepository orderRepository) {
        this.retailerRepository = retailerRepository;
        this.orderRepository = orderRepository;
    }

    @GetMapping
    public String orderHomePage(Model model){
        List<Order> orderList = this.orderRepository.findAll();
        model.addAttribute("orderList", orderList);
        return "order/orders";
    }

    @GetMapping("/{orderId}")
    public String viewOrderById(@PathVariable String orderId, Model model){
        Optional<Order> order = this.orderRepository.findById(orderId);
        Optional<Retailer> retailer = this.retailerRepository.findById(order.get().getRetailerId());

        List<String> orderStatusList = Stream.of(OrderStatus.values())
                .map(OrderStatus::name).collect(Collectors.toList());

        model.addAttribute("orderStatusList", orderStatusList);
        model.addAttribute("retailer", retailer);
        model.addAttribute("order", order.get());
        model.addAttribute("itemList", new ArrayList<Object>());
        return "order/order-info";
    }

    @GetMapping("/create-order")
    public String createOrderForm(Model model){
        List<Retailer> retailerList = this.retailerRepository.findAll();
        model.addAttribute("retailerList", retailerList);
        model.addAttribute("orderForm", new Order());
        model.addAttribute("retailer", new Retailer());
        return "order/create-order";
    }

    @PostMapping("/create-order")
    public String createOrder(@ModelAttribute("orderForm") Order order, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "redirect:/orders/create-order";
        }
        order.setDate(new Date());
        order.setStatus(OrderStatus.PENDING);
        Optional<Retailer> retailer = this.retailerRepository.findById(order.getRetailerId());
        retailer.ifPresent(value -> order.getShipment().setAddress(value.getAddress()));
        this.orderRepository.save(order);
        return "redirect:/orders";
    }

    @GetMapping("/delete/{orderId}")
    public String deleteOrder(@PathVariable String orderId){
        this.orderRepository.deleteById(orderId);
        return "redirect:/orders";
    }

    @PostMapping("/update/{orderId}")
    public String updateOrder(@ModelAttribute("order") Order order, @PathVariable String orderId){
        Optional<Order> orderDetail = this.orderRepository.findById(orderId);
        orderDetail.get().setStatus(order.getStatus());
        orderDetail.get().getShipment().setCarrier(order.getShipment().getCarrier());
        if(order.getShipment().getOrigin() != null) {
            orderDetail.get().getShipment().setOrigin(order.getShipment().getOrigin());
        }
        this.orderRepository.save(orderDetail.get());
        return "redirect:/orders/" + orderId;
    }

}
