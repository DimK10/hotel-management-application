package com.sphy.hotelmanagementapplication.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/***
 * created by gp , AKd
 */
@Entity
@Table(name = "hotels")
@NamedEntityGraph(name = "Hotel.rooms",
		attributeNodes = @NamedAttributeNode("rooms")
)
@AttributeOverride(name = "id", column = @Column(name = "hotel_id")) // created by AKd
public class Hotel extends BaseEntity {


    @Column(name = "name")
    private String name;
    @Column(name = "stars")
    private int stars;
    @Column(name = "area_name")
    private String areaName;

    private String address;

    private boolean disabled;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;

    @OneToMany(mappedBy = "hotel", fetch =  FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Set<Room> rooms = new HashSet<>();


    @ManyToMany(fetch = FetchType.EAGER)  // created by AKd
	@JoinTable(  // created by AKd
			name = "hotel_amenity",  // created by AKd
			joinColumns = @JoinColumn(name = "hotel_id"),  // created by AKd
			inverseJoinColumns = @JoinColumn(name = "HAmenity_id") // created by AKd
	)
	private Set<HotelAmenity> hotelAmenity = new HashSet<>();// created by AKd


    public Hotel(Long id) {
        super(id);
    }

    public Hotel() {
    }

    public Hotel(Long id, String name, int stars, String areaName, boolean disabled) {
		super(id);
        this.name = name;
        this.stars = stars;
        this.areaName = areaName;
        this.disabled = disabled;
    }

	public Long getId() {
		return super.getId();
	}

	public void setId(Long id) {
		super.setId(id);
	}

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Set<Room> getRooms() {
        return rooms;
    }

    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

	// created by AKd
	public Set<HotelAmenity> getHotelAmenity(){
		return hotelAmenity;
	}

	// created by AKd
	public void setHotelAmenity(Set<HotelAmenity> hotelAmenity) {
		this.hotelAmenity = hotelAmenity;
	}
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
	public boolean equals(Object o) {
		return super.equals(o);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
    public String toString() {
        return "Hotel{" +
                "id=" + super.getId() +
                ", name='" + name + '\'' +
                ", stars=" + stars +
                ", areaName='" + areaName + '\'' +
                ", address='" + address + '\'' +
                ", owner=" + owner +
//                ", rooms=" + rooms +
                '}';
    }
}
