package sia.tacos.controller;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sia.tacos.model.Order;
import sia.tacos.repository.OrderRepository;

@RestController
@RequestMapping(path = "/orders", produces = "application/json")
@CrossOrigin(origins = "*")
public class OrderController {

    private OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping(produces = "application/json")
    public Iterable<Order> allOrders() {
        return orderRepository.findAll();
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Order postOrder(@RequestBody Order order) {
        return orderRepository.save(order);
    }

    @PutMapping(path="/{orderId}", consumes = "application/json")
    public Order putOrder(@RequestBody Order order) {
        return orderRepository.save(order);
    }

    @PatchMapping(path="/{orderId}", consumes = "application/json")
    public Order patchOrder(@PathVariable("orderId") Long orderId,
                            @RequestBody Order path) {
        Order order = orderRepository.findById(orderId).get();

        if(path.getClientName() != null) {
            order.setClientName(path.getClientName());
        }
        if(path.getStreet() != null) {
            order.setStreet(path.getStreet());
        }
        if(path.getCity() != null) {
            order.setCity(path.getCity());
        }
        if(path.getState() != null) {
            order.setState(path.getState());
        }
        if(path.getZip() != null) {
            order.setZip(path.getZip());
        }
        if(path.getCcNumber() != null) {
            order.setCcNumber(path.getCcNumber());
        }
        if(path.getCcExpiration() != null) {
            order.setCcExpiration(path.getCcExpiration());
        }
        if(path.getCcCVV() != null) {
            order.setCcCVV(path.getCcCVV());
        }

        return orderRepository.save(order);
    }

    @DeleteMapping("/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable("orderId") Long orderId) {
        try {
            orderRepository.deleteById(orderId);
        } catch (EmptyResultDataAccessException e) {}
    }
}
