package com.sphy.hotelmanagementapplication.converter;

import com.sphy.hotelmanagementapplication.domain.Client;
import com.sphy.hotelmanagementapplication.dto.ClientDTO;
import com.sphy.hotelmanagementapplication.dto.OrderDTO;
import org.springframework.stereotype.Component;

/***
 * created by gp
 */
@Component
public class ClientDTOToClient {

    private final OrderDTOToOrder orderDTOToOrder;

    public ClientDTOToClient(OrderDTOToOrder orderDTOToOrder) {
        this.orderDTOToOrder = orderDTOToOrder;
    }

    /***
     * converts a clientDTO object to client
     * @param clientDTO the clint object to be converted
     * @return the converted Client object
     */
    public Client converter(ClientDTO clientDTO) {
        Client client = new Client();
        client.setId(clientDTO.getId());
        client.setEmail(clientDTO.getEmail());
        client.setFirstname(clientDTO.getFirstname());
        client.setLastname(clientDTO.getLastname());
        client.setUsername(clientDTO.getUsername());
        client.setHashedPassword(clientDTO.getHashedPassword());
        client.setEmailVerify(clientDTO.isEmailVerify());
        client.setTransactionId(clientDTO.getTransactionId());

        for (OrderDTO orderDTO : clientDTO.getOrders()) {
            client.getOrders().add(orderDTOToOrder.Converter(orderDTO));
        }

        return client;
    }
}
