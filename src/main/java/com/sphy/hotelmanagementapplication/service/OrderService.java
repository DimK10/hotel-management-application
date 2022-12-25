package com.sphy.hotelmanagementapplication.service;

import com.sphy.hotelmanagementapplication.converter.OrderDTOToOrder;
import com.sphy.hotelmanagementapplication.converter.OrderToOrderDTO;
import com.sphy.hotelmanagementapplication.domain.*;
import com.sphy.hotelmanagementapplication.dto.OrderDTO;
import com.sphy.hotelmanagementapplication.exception.ApiExceptionFront;
import com.sphy.hotelmanagementapplication.exception.ApiRequestException;
import com.sphy.hotelmanagementapplication.repositories.OrderRepository;
import com.sphy.hotelmanagementapplication.repositories.RoomRepository;
import com.sphy.hotelmanagementapplication.repositories.UserRepository;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/***
 * created by gp on 08/11/22
 */
@Service
public class OrderService {

    private final OrderRepository orderRepository;

    private final RoomRepository roomRepository;

    private final UserRepository userRepository;

    private final OrderDTOToOrder orderDTOToOrder;

    private final OrderToOrderDTO orderToOrderDTO;

    public OrderService(OrderRepository orderRepository, RoomRepository roomRepository, UserRepository userRepository, OrderDTOToOrder orderDTOToOrder, OrderToOrderDTO orderToOrderDTO) {
        this.orderRepository = orderRepository;
        this.roomRepository = roomRepository;
        this.userRepository = userRepository;
        this.orderDTOToOrder = orderDTOToOrder;
        this.orderToOrderDTO = orderToOrderDTO;
    }

    /***
     * save an order
     * @param orderDTO the order to be saved
     * @return the saved order for confirmation
     * @throws ApiRequestException when there is no client or the client does not exist
     */
    public OrderDTO saveOrderDTO(OrderDTO orderDTO) throws ApiRequestException {
        boolean doesNotExist = false;

        Optional<Room> room = roomRepository.findById(orderDTO.getRoom());
        Optional<User> client = userRepository.findById(orderDTO.getClient());

        Order order = orderDTOToOrder.converter(orderDTO);

        if (client.isEmpty()){
            throw new ApiRequestException("There is no client or the clint dies not exist in the order");
        }
        if (!room.isPresent()){
            throw new ApiRequestException("There is no room or the room does not exist in the order");
        }

            int conflict = 0;

            conflict = orderRepository.OrderConflict(order.getCheckInDate(),order.getCheckOutDate(),room.get());

            if (conflict == 0){
                return orderToOrderDTO.converter(orderRepository.save(order));
            }else {
                throw new ApiExceptionFront("The room isn't available on the desirable dates");
            }
    }

    /***
     * get all orders
     * @return a list of all orders
     * @throws ApiRequestException if no orders are saved
     */
    public List<OrderDTO> getOrders() throws ApiRequestException {


        List<Order> orders = new ArrayList<>();

        orderRepository.findAll().forEach(orders::add);


        List<OrderDTO> ordersDTO = new ArrayList<>();

        for (Order order : orders) {
            ordersDTO.add(orderToOrderDTO.converter(order));
        }
        return ordersDTO;

    }

    /***
     * find an order by his id
     * @param id of the order to be found
     * @return the order with the current id
     * @throws ApiRequestException if there is no order with the given id
     */
    public OrderDTO getOrderById(Long id) throws ApiRequestException {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isEmpty()){
            throw new ApiRequestException("There is now order with id: " + id);
        }else {
            return orderToOrderDTO.converter(orderRepository.findById(id).get());
        }
    }



    /***
     * enables an order
     * @param id of the order to be enabled
     * @return a boolean if  the order enabled or not
     * @throws ApiExceptionFront if the order does not exist or is already activated
     */
    public boolean enableOrder(Long id) throws ApiExceptionFront {
        if (!orderRepository.existsById(id)) {
            throw  new ApiExceptionFront("The order with id: " + id + " does not exist");
        }else if (!orderRepository.findById(id).get().isCanceled()){
            throw new ApiExceptionFront("The order with id: " + id + " is already activated");
        }else {
            Order order = orderRepository.findById(id).get();
            order.setCanceled(false);
            orderRepository.save(order);
            return true;
        }
    }

    /***
     * cancel an order by his id
     * @param id of the order to be canceled
     * @return a boolean if the order canceled or not
     * @throws ApiExceptionFront if the order does not exist or is already canceled
     */
    public boolean disableOrder(Long id) throws ApiExceptionFront {

        if (!orderRepository.existsById(id)) {
            throw new ApiExceptionFront("The order with id:" + id + " does not exist");
        }else if (orderRepository.findById(id).get().isCanceled() ){
            throw new ApiExceptionFront("The order with id: " + id + "is already canceled");
        }else {
            Order order = orderRepository.findById(id).get();
            order.setCanceled(true);
            orderRepository.save(order);
            return true;
        }
    }

    /***
     * updates an order
     * @param orderDTO order to be updated
     * @return the updated order for confirmation
     * @throws ApiRequestException if the order that is going to update is not exists
     */
    public OrderDTO updateOrder(OrderDTO orderDTO) throws ApiRequestException {
        if (orderDTO.getId() == null){
            throw new ApiRequestException("The order id can't be null");
        }
        Optional<Order> order = orderRepository.findById(orderDTO.getId());

        if (order.isPresent()){
            Order existingOrder = orderRepository.findById(orderDTO.getId()).orElse(null);

            int conflict = 0;

            conflict = orderRepository.OrderConflict(order.get().getCheckInDate(),order.get().getCheckOutDate(),order.get().getRoom());

            if (conflict == 0){
                existingOrder.setCheckOutDate(orderDTO.getCheckOutDate());
                existingOrder.setCheckInDate(orderDTO.getCheckInDate());
                existingOrder.setCanceled(orderDTO.isCanceled());
                orderRepository.save(order.get());
            }else {
                throw new ApiExceptionFront("The room isn't available on the desirable dates");
            }

//            existingOrder.setClient(clientRepository.findById(orderDTO.getClient()).get());
//            existingOrder.setRoom(roomRepository.findById(orderDTO.getRoom()).get());
//            Optional<Room> room = roomRepository.findById(orderDTO.getRoom());
//            if (room.isPresent()){
//                room.get().getOrders().add(existingOrder);
//            }

            return orderToOrderDTO.converter(orderRepository.save(existingOrder));
        }else{

            throw new ApiRequestException("The order with id: " + orderDTO.getId() + " does not exist");
        }
    }
}

