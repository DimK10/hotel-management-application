package com.sphy.hotelmanagementapplication.controller;

import com.sphy.hotelmanagementapplication.dto.OrderDTO;
import com.sphy.hotelmanagementapplication.exception.ApiRequestException;
import com.sphy.hotelmanagementapplication.service.HotelService;
import com.sphy.hotelmanagementapplication.service.OrderService;
import com.sphy.hotelmanagementapplication.service.RoomService;
import com.sphy.hotelmanagementapplication.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/***
 * created by gp on 08/11/22
 */
@RestController
public class OrderController {

    private final OrderService service;

    private final UserService userService;

    private final RoomService roomService;

    private final HotelService hotelService;


    public OrderController(OrderService service, UserService userService, RoomService roomService, HotelService hotelService) {
        this.service = service;

        this.userService = userService;
        this.roomService = roomService;

        this.hotelService = hotelService;
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
     * finds all Client's orders
     * @return all Client's orders
     * @throws ApiRequestException if no orders is saved
     */
    @GetMapping("/api/orders/client")
    @PreAuthorize("hasAuthority('CLIENT')")
    public List<OrderDTO> findAllOrdersClient(@RequestHeader(name = "Authorization") String token) throws ApiRequestException {

        return service.getOrdersClient(userService.getUserFromToken(token).getId());
    }

    /***
     * finds all Admins orders
     * @return all Admins orders
     * @throws ApiRequestException if no orders is saved
     */
    @GetMapping("/api/orders/admin")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<OrderDTO> findAllOrdersAdmin(@RequestHeader(name = "Authorization") String token) throws ApiRequestException {

        return service.getOrdersAdmin(userService.getUserFromToken(token).getId());
    }

    /***
     * finds an order by his id
     * @param id id of the order we want to save
     * @return the order with the given id
     * @throws ApiRequestException if there is no order with the given id
     */
    @GetMapping("/api/orderId/{id}")
    public OrderDTO findOrderById(@RequestHeader(name = "Authorization") String token, @PathVariable Long id) throws ApiRequestException {

        OrderDTO order = service.getOrderById(id);

        if (Objects.equals(userService.getUserById(order.getClient()), userService.getUserFromToken(token))
        || Objects.equals(id, hotelService.getHotelById(roomService.getRoomById(order.getRoom()).getHotel()).getOwner())) {

            return order;
        }else {

            throw  new RuntimeException("Unauthorized");
        }
    }


    /***
     * updates an order
     * @param orderDTO the order we want to update
     * @return the updated order for confirmation
     * @throws ApiRequestException if the order that is going to update is not exists
     */
    @PutMapping("/api/order/update")
    @PreAuthorize("hasAuthority('CLIENT')")
    public OrderDTO updateOrder(@RequestHeader(name = "Authorization") String token, @RequestBody OrderDTO orderDTO) throws ApiRequestException {

        if (Objects.equals(userService.getUserFromToken(token), userService.getUserById(orderDTO.getClient()))) {

            return service.updateOrder(orderDTO);

        } else {

            throw new RuntimeException("Unauthorized");
        }

    }

    /***
     * enables an order by his id
     * @param id id of the order we want to disable
     * @return a confirmation message or an error message
     * @throws ApiRequestException if the order does not exist or is already activated
     */
    @PostMapping("/api/order/enable/{id}")
    @PreAuthorize("hasAuthority('CLIENT')")
    ResponseEntity<String> enableOrder(@RequestHeader(name = "Authorization") String token, @PathVariable Long id) throws ApiRequestException {

        if (Objects.equals(userService.getUserFromToken(token), userService.getUserById(service.getOrderById(id).getClient()))) {

            service.enableOrder(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Order with id " + id + " was successfully activated");

        } else {

            throw new RuntimeException("Unauthorized");
        }
    }


    /***
     * disables an order by his id
     * @param id of the order we want to disable
     * @return a message of confirmation or an error message
     * @throws ApiRequestException if the order does not exist or is already deactivated
     */
    @PostMapping("/api/order/disable/{id}")
    @PreAuthorize("hasAuthority('CLIENT')")
    ResponseEntity<String> disableRoom(@RequestHeader(name = "Authorization") String token, @PathVariable Long id) throws ApiRequestException {

        if (Objects.equals(userService.getUserFromToken(token), userService.getUserById(service.getOrderById(id).getClient()))) {

            service.disableOrder(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Order with id " + id + " was successfully deactivated");

        } else {

            throw new RuntimeException("Unauthorized");
        }
    }
}

