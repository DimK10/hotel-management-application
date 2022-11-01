package com.sphy.hotelmanagementapplication.converter;

import com.sphy.hotelmanagementapplication.domain.Client;
import com.sphy.hotelmanagementapplication.domain.Order;
import com.sphy.hotelmanagementapplication.dto.ClientDTO;
import org.springframework.stereotype.Component;

@Component
public class ClientToClientDTO {

    private final OrderToOrderDTO orderToOrderDTO;

    public ClientToClientDTO(OrderToOrderDTO orderToOrderDTO) {
        this.orderToOrderDTO = orderToOrderDTO;
    }

    public ClientDTO converter(Client client){
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setId(client.getId());
        clientDTO.setEmail(client.getEmail());
        clientDTO.setFirstname(client.getFirstname());
        clientDTO.setLastname(client.getLastname());
        clientDTO.setUsername(client.getUsername());
        clientDTO.setHashedPassword(client.getHashedPassword());
        clientDTO.setEmailVerify(client.isEmailVerify());
        clientDTO.setTransactionId(client.getTransactionId());

        for (Order order:client.getOrders()){
            clientDTO.getOrders().add(orderToOrderDTO.Converter(order));
        }

        return clientDTO;

    }
}
