package pl.biedzki.hotel.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "Reservation")
@JsonIgnoreProperties(value = { "roomEntity" })
public class ReservationEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	private LocalDate checkin;

	@NotNull
	private LocalDate checkout;
	
	@ManyToOne
	private RoomEntity roomEntity;

	public ReservationEntity() {
		super();
	}

	public ReservationEntity(@NotNull LocalDate checkin, @NotNull LocalDate checkout) {
		super();
		this.checkin = checkin;
		this.checkout = checkout;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getCheckin() {
		return checkin;
	}

	public void setCheckin(LocalDate checkin) {
		this.checkin = checkin;
	}

	public LocalDate getCheckout() {
		return checkout;
	}

	public void setCheckout(LocalDate checkout) {
		this.checkout = checkout;
	}

	public RoomEntity getRoomEntity() {
		return roomEntity;
	}

	public void setRoomEntity(RoomEntity roomEntity) {
		this.roomEntity = roomEntity;
	}

	@Override
	public String toString() {
		return "ReservationEntity [id=" + id + ", checkin=" + checkin + ", checkout=" + checkout + ", roomNumber="
				+ roomEntity.getRoomNumber() + "]";
	}

	
	
}
