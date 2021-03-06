package pl.biedzki.hotel.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="Room")
//@JsonIgnoreProperties(value = { "reservationEntityList" })
public class RoomEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull
	private Integer roomNumber;
	
	@NotNull
	private String price;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private List<ReservationEntity> reservationEntityList;
	
	public RoomEntity() {
		super();
	}
	
	public Long getId() {
		return id;
	}

	public RoomEntity(@NotNull Integer roomNumber, @NotNull String price) {
		super();
		this.roomNumber = roomNumber;
		this.price = price;
	}

	public Integer getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public List<ReservationEntity> getReservationEntityList() {
		return reservationEntityList;
	}

	public void setReservationEntityList(List<ReservationEntity> reservationEntityList) {
		this.reservationEntityList = reservationEntityList;
	}
	
	public void addReservationEntity(ReservationEntity reservationEntity) {
		if(null == reservationEntityList) reservationEntityList = new ArrayList<>();
		
		reservationEntityList.add(reservationEntity);
	}

	@Override
	public String toString() {
		return "RoomEntity [id=" + id + ", roomNumber=" + roomNumber + ", price=" + price + ", reservationEntityList="
				+ reservationEntityList + "]";
	}

	

	
		
}
