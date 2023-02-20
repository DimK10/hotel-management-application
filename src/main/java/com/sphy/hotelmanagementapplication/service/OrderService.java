package com.sphy.hotelmanagementapplication.service;

import com.sphy.hotelmanagementapplication.converter.OrderDTOToOrder;
import com.sphy.hotelmanagementapplication.converter.OrderToOrderDTO;
import com.sphy.hotelmanagementapplication.domain.Order;
import com.sphy.hotelmanagementapplication.domain.Room;
import com.sphy.hotelmanagementapplication.domain.User;
import com.sphy.hotelmanagementapplication.dto.OrderDTO;
import com.sphy.hotelmanagementapplication.exception.ApiExceptionFront;
import com.sphy.hotelmanagementapplication.exception.ApiRequestException;
import com.sphy.hotelmanagementapplication.repository.OrderRepository;
import com.sphy.hotelmanagementapplication.repository.RoomRepository;
import com.sphy.hotelmanagementapplication.repository.UserRepository;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.*;

/***
 * created by gp on 08/11/22
 */
@Service
public class OrderService {

    private final EntityManager entityManager;

    private final OrderRepository orderRepository;

    private final RoomRepository roomRepository;

    private final UserRepository userRepository;

    private final OrderDTOToOrder orderDTOToOrder;

    private final OrderToOrderDTO orderToOrderDTO;

    private final JavaMailSender emailSender;

    public OrderService(EntityManager entityManager, OrderRepository orderRepository, RoomRepository roomRepository, UserRepository userRepository, OrderDTOToOrder orderDTOToOrder, OrderToOrderDTO orderToOrderDTO, JavaMailSender emailSender) {
        this.entityManager = entityManager;
        this.orderRepository = orderRepository;
        this.roomRepository = roomRepository;
        this.userRepository = userRepository;
        this.orderDTOToOrder = orderDTOToOrder;
        this.orderToOrderDTO = orderToOrderDTO;
        this.emailSender = emailSender;
    }

    /***
     * save an order
     * @param orderDTO the order to be saved
     * @return the saved order for confirmation
     * @throws ApiRequestException when there is no client or the client does not exist
     */
    public OrderDTO saveOrderDTO(OrderDTO orderDTO) throws ApiRequestException {

        Optional<Room> room = roomRepository.findById(orderDTO.getRoom());
        Optional<User> client = userRepository.findById(orderDTO.getClient());

        Order order = orderDTOToOrder.converter(orderDTO);

        if (client.isEmpty()) {
            throw new ApiRequestException("There is no client or the clint dies not exist in the order");
        }
        if (room.isEmpty()) {
            throw new ApiRequestException("There is no room or the room does not exist in the order");
        }

        int conflict;

        synchronized (this) {
            conflict = orderRepository.OrderConflict(order.getCheckInDate(), order.getCheckOutDate(), room.get());

            if (conflict == 0) {

                Order order1 = orderRepository.save(order);

                // Created by Akd

                if (order1 != null) {

                    String subject = "Order Confirmation";
                    String text = "Your order has been saved successfully.\n"
                            + "Order details:\n"
                            + "Hotel: " + order.getRoom().getHotel().getName() + "\n"
                            + "Room: " + order.getRoom().getName() + "\n"
                            + "Check-in date: " + order.getCheckInDate() + "\n"
                            + "Check-out date: " + order.getCheckOutDate() + "\n"
                            + "Total price is: " + order.getPrice() + "\n\n"
                            + "Thank you for choosing our hotel!";

                    SimpleMailMessage message = new SimpleMailMessage();
                    message.setFrom("hotelmanagement140@gmail.com");
                    message.setTo(client.get().getEmail());
                    message.setSubject(subject);
                    message.setText(text);

                    emailSender.send(message);

                }
                return orderToOrderDTO.converter(order1);

            } else {
                throw new ApiExceptionFront("The room isn't available on the desirable dates");
            }
        }

    }

    /***
     * get all Client's orders
     * @return a list of all Client's orders
     * @throws ApiRequestException if no orders are saved
     */
    public List<OrderDTO> getOrdersClient(Long id) throws ApiRequestException {

        List<Order> orders = new ArrayList<>(orderRepository.findAllClient(id));

        List<OrderDTO> ordersDTO = new ArrayList<>();

        orders.forEach(order -> ordersDTO.add(orderToOrderDTO.converter(order)));

        return ordersDTO;

    }

    /***
     * get all orders
     * @return a list of all orders
     * @throws ApiRequestException if no orders are saved
     */
    public List<OrderDTO> getOrdersAdmin(Long id, String firstName, String lastName, Integer pageNo, Integer pageSize) throws ApiRequestException {

        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.unsorted());

        Map<String, Object> parametrMap = new HashMap<>();

        StringBuilder query = new StringBuilder("select o from orders o where o.room.hotel.owner.id = :id ");
        parametrMap.put("id", id);

        if (firstName != null){

            query.append(" and o.client.firstname like :firstName ");
            parametrMap.put("firstName", "%" + firstName + "%" );

        }

        if ( lastName != null){

            query.append(" and o.client.lastname like :lastName ");
            parametrMap.put("lastName", "%" + lastName + "%" );
        }

        Query queryFinal = entityManager.createQuery(String.valueOf(query), Order.class);

        for (String key : parametrMap.keySet()) {

            queryFinal.setParameter(key, parametrMap.get(key));
        }

        List<Order> orders = queryFinal.getResultList();



        List<OrderDTO> ordersDTO = new ArrayList<>();

        orders.forEach(order -> ordersDTO.add(orderToOrderDTO.converter(order)));

        Page<OrderDTO> orderDTOS1 = new PageImpl<>(ordersDTO, paging, ordersDTO.size());

        if (!orderDTOS1.isEmpty()) {
            return orderDTOS1.getContent();

        } else {
            return new ArrayList<>() {
            };
        }
    }

    /***
     * find an order by his id
     * @param id of the order to be found
     * @return the order with the current id
     * @throws ApiRequestException if there is no order with the given id
     */
    public OrderDTO getOrderById(Long id) throws ApiRequestException {
        Optional<Order> order = orderRepository.findById(id);

        if (order.isEmpty()) {
            throw new ApiRequestException("There is now order with id: " + id);
        } else {
            return orderToOrderDTO.converter(order.get());
        }
    }


    /***
     * enables an order
     * @param id of the order to be enabled
     * @return a boolean if  the order enabled or not
     * @throws ApiExceptionFront if the order does not exist or is already activated
     */
    public boolean enableOrder(Long id) throws ApiExceptionFront {

        Optional<Order> order = orderRepository.findById(id);

        if (order.isEmpty()) {
            throw new ApiExceptionFront("The order with id: " + id + " does not exist");
        } else if (!order.get().isCanceled()) {
            throw new ApiExceptionFront("The order with id: " + id + " is already activated");
        } else {
            order.get().setCanceled(false);
            orderRepository.save(order.get());
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

        Optional<Order> order = orderRepository.findById(id);


        if (order.isEmpty()) {
            throw new ApiExceptionFront("The order with id:" + id + " does not exist");
        } else if (order.get().isCanceled()) {
            throw new ApiExceptionFront("The order with id: " + id + "is already canceled");
        } else {
            order.get().setCanceled(true);
            orderRepository.save(order.get());
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

        Optional<Order> orderOptional = orderRepository.findById(orderDTO.getId());

        if (orderOptional.isEmpty()) {
            throw new ApiRequestException("The order with id: " + orderDTO.getId() + " does not exist");
        }

        int conflict;

        Optional<Room> room = roomRepository.findById(orderDTO.getRoom());

        if (room.isEmpty()) {
            throw new RuntimeException("The room can't be empty");
        }

        synchronized (this) {

            conflict = orderRepository.OrderConflict(orderDTO.getCheckInDate(),
                    orderDTO.getCheckOutDate(), room.get());

            if (conflict == 0) {
                orderOptional.get().setCheckOutDate(orderDTO.getCheckOutDate());
                orderOptional.get().setCheckInDate(orderDTO.getCheckInDate());
                orderOptional.get().setCanceled(orderDTO.isCanceled());
                orderOptional.get().setPrice(orderDTO.getPrice());
                orderOptional.get().setHotelName(orderDTO.getHotelName());
                orderOptional.get().setRoomName(orderDTO.getRoomName());
                orderOptional.get().getRoomAmenities().forEach(amenity -> orderDTO.getRoomAmenities().add(amenity));
                orderOptional.get().getHotelAmenities().forEach(amenity -> orderDTO.getRoomAmenities().add(amenity));

            } else {
                throw new ApiExceptionFront("The room isn't available on the desirable dates");
            }
        }

        return orderToOrderDTO.converter(orderRepository.save(orderOptional.get()));

    }

    /***
     * get all orders
     * @return a list of all orders
     * @throws ApiRequestException if no orders are saved
     */
    public List<OrderDTO> getOrdersAdminAll(Long id) {

        List<OrderDTO> orderDTOS = new ArrayList<>();

        orderRepository.findAllAdmin(id).forEach(order -> orderDTOS.add(orderToOrderDTO.converter(order)));

        return orderDTOS;
    }
}

