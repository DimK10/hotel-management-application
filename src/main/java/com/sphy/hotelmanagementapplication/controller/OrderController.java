package com.sphy.hotelmanagementapplication.controller;

import com.sphy.hotelmanagementapplication.dto.OrderDTO;
import com.sphy.hotelmanagementapplication.exception.ApiRequestException;
import com.sphy.hotelmanagementapplication.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/***
 * created by gp on 08/11/22
 */
@RestController
public class OrderController {

    private final OrderService service;


    public OrderController(OrderService service) {
        this.service = service;

    }

    /***
     * creates a new order
     * @param orderDTO is the order to be saved
     * @return the saved order for confirmation
     * @throws ApiRequestException when there is no client or the client does not exist
     */
    @PostMapping("/api/order/create")
    public OrderDTO addOrder(@RequestBody OrderDTO orderDTO) throws ApiRequestException {

        return service.saveOrderDTO(orderDTO);
    }


    /***
     * finds all orders
     * @return all orders
     * @throws ApiRequestException if no orders is saved
     */
    @GetMapping("/api/orders")
    public List<OrderDTO> findAllOrders(@RequestHeader(name = "Authorization") String token) throws ApiRequestException {

        return service.getOrders();
    }

    /***
     * finds an order by his id
     * @param id id of the order we want to save
     * @return the order with the given id
     * @throws ApiRequestException if there is no order with the given id
     */
    @GetMapping("/api/orderId/{id}")
    public OrderDTO findOrderById(@PathVariable Long id) throws ApiRequestException {

        return service.getOrderById(id);
    }


    /***
     * updates an order
     * @param orderDTO the order we want to update
     * @return the updated order for confirmation
     * @throws ApiRequestException if the order that is going to update is not exists
     */
    @PutMapping("/api/order/update")
    public OrderDTO updateOrder(@RequestBody OrderDTO orderDTO)throws ApiRequestException {

        return service.updateOrder(orderDTO);

    }

    /***
     * enables an order by his id
     * @param id id of the order we want to disable
     * @return a confirmation message or an error message
     * @throws ApiRequestException if the order does not exist or is already activated
     */
    @PostMapping("/api/order/enable/{id}")
    ResponseEntity<String> enableOrder(@PathVariable Long id) throws ApiRequestException {

        service.enableOrder(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Order with id " + id + " was successfully activated");

    }


    /***
     * disables an order by his id
     * @param id of the order we want to disable
     * @return a message of confirmation or an error message
     * @throws ApiRequestException if the order does not exist or is already deactivated
     */
    @PostMapping("/api/order/disable/{id}")
    ResponseEntity<String> disableRoom(@PathVariable Long id) throws ApiRequestException {

        service.disableOrder(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Order with id " + id + " was successfully deactivated");

    }
}

