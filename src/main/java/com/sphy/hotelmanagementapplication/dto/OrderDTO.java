package com.sphy.hotelmanagementapplication.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/***
 * created by gp
 */
public class OrderDTO implements Serializable {

    private Long id;

    private LocalDate checkInDate;

    private LocalDate checkOutDate;

    private Long client;

    private Long room;

    private boolean canceled;

    public OrderDTO() {
    }

    public OrderDTO(long l) {
        this.id = l;
    }


    public boolean isCanceled() {
        return canceled;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

	public Long getClient() {
		return client;
	}

	public void setClient(Long client) {
		this.client = client;
	}

	public Long getRoom() {
		return room;
	}

	public void setRoom(Long room) {
		this.room = room;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDTO orderDTO = (OrderDTO) o;
        return canceled == orderDTO.canceled && Objects.equals(id, orderDTO.id) && Objects.equals(checkInDate, orderDTO.checkInDate) && Objects.equals(checkOutDate, orderDTO.checkOutDate) && Objects.equals(client, orderDTO.client) && Objects.equals(room, orderDTO.room);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, checkInDate, checkOutDate, client, room, canceled);
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "id=" + id +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                ", client=" + client +
                ", room=" + room +
                '}';
    }
}
