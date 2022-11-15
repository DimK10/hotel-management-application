package com.sphy.hotelmanagementapplication.converter;

import com.sphy.hotelmanagementapplication.domain.Client;
import com.sphy.hotelmanagementapplication.domain.Order;
import com.sphy.hotelmanagementapplication.dto.ClientDTO;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/***
 * created by gp
 */
@Component
public class ClientToClientDTO {

    private final OrderToOrderDTO orderToOrderDTO;

    public ClientToClientDTO(OrderToOrderDTO orderToOrderDTO) {
        this.orderToOrderDTO = orderToOrderDTO;
    }

    /***
     * converts a client object to clientDTO
     * @param client the client object we want to convert
     * @return the converted clientDTO object
     */
    @Transactional
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
            clientDTO.getOrders().add(orderToOrderDTO.converter(order));
        }

        return clientDTO;

    }
}
