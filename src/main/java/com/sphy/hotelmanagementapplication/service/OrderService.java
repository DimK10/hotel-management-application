package com.sphy.hotelmanagementapplication.service;

import com.sphy.hotelmanagementapplication.converter.OrderDTOToOrder;
import com.sphy.hotelmanagementapplication.converter.OrderToOrderDTO;
import com.sphy.hotelmanagementapplication.domain.*;
import com.sphy.hotelmanagementapplication.dto.OrderDTO;
import com.sphy.hotelmanagementapplication.exception.ApiExceptionFront;
import com.sphy.hotelmanagementapplication.exception.ApiRequestException;
import com.sphy.hotelmanagementapplication.repositories.ClientRepository;
import com.sphy.hotelmanagementapplication.repositories.OrderRepository;
import com.sphy.hotelmanagementapplication.repositories.RoomRepository;
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

    private final ClientRepository clientRepository;

    private final OrderDTOToOrder orderDTOToOrder;

    private final OrderToOrderDTO orderToOrderDTO;

    public OrderService(OrderRepository orderRepository, RoomRepository roomRepository, ClientRepository clientRepository, OrderDTOToOrder orderDTOToOrder, OrderToOrderDTO orderToOrderDTO) {
        this.orderRepository = orderRepository;
        this.roomRepository = roomRepository;
        this.clientRepository = clientRepository;
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
        Order order = new Order();

        Optional<Client> clientOpt =
                clientRepository.findById(orderDTO.getClient());

        order = orderDTOToOrder.converter(orderDTO);

        if (clientOpt.isPresent()){
            clientOpt.get().getOrders().add(order);
            clientRepository.save(clientOpt.get());
        }else if (!clientOpt.isPresent()){
            throw  new ApiRequestException("There is no client that order belongs to");
        }else {
            throw  new ApiRequestException("The clint does not exists");

        }

        return orderToOrderDTO.converter(order);
    }

    /***
     * saves a list of orders
     * @param ordersDTO the orders to be saved
     * @return the saved orders
     * @throws ApiRequestException when an order doesn't have a client or the client does not exist
     */
    public List<OrderDTO> saveOrders(List<OrderDTO> ordersDTO) throws ApiRequestException {
        List<Order> orders = new ArrayList<>();

        for(OrderDTO orderDto : ordersDTO) {
            if (orderDto.getClient() == null) {
                throw new ApiRequestException(
                        " Order with id: " + orderDto.getId() + " has not have a clint"
                );
            }else if (!clientRepository.findById(orderDto.getClient()).isPresent()){
                throw new ApiRequestException("Client with id: " + orderDto.getClient() + " does not exist");
            }else {
                orders.add(orderDTOToOrder.converter(orderDto));
            }
        }
        Iterable<Order> ordersSaved = orderRepository.saveAll(orders);

        ordersSaved.spliterator().forEachRemaining(orders::add);

        List<OrderDTO> ordersDTOS = new ArrayList<>();
        for (Order order : orders){
            ordersDTOS.add(orderToOrderDTO.converter(order));
        }
        return ordersDTOS;
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
        if (!order.isPresent()){
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
        Optional<Order> order = orderRepository.findById(orderDTO.getId());
        if (order.isPresent()){
            Order existingOrder = orderRepository.findById(orderDTO.getId()).orElse(null);
            existingOrder.setCheckOutDate(orderDTO.getCheckOutDate());
            existingOrder.setCheckInDate(orderDTO.getCheckInDate());
            existingOrder.setClient(clientRepository.findById(orderDTO.getClient()).get());
            existingOrder.setRoom(roomRepository.findById(orderDTO.getRoom()).get());
            existingOrder.setCanceled(orderDTO.isCanceled());
            Optional<Room> room = roomRepository.findById(orderDTO.getRoom());
            if (room.isPresent()){
                room.get().getOrders().add(existingOrder);
            }
            return orderToOrderDTO.converter(orderRepository.save(existingOrder));
        }else{

            throw new ApiRequestException("The order with id: " + orderDTO.getId() + " does not exist");
        }
    }
}

