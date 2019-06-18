package pl.biedzki.hotel.rest;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pl.biedzki.hotel.converter.RoomEntityToReservableRoomResponseConverter;
import pl.biedzki.hotel.entity.ReservationEntity;
import pl.biedzki.hotel.entity.RoomEntity;
import pl.biedzki.hotel.model.request.ReservationRequest;
import pl.biedzki.hotel.model.response.ReservableRoomResponse;
import pl.biedzki.hotel.model.response.ReservationResponse;
import pl.biedzki.hotel.repository.PageableRoomRepository;
import pl.biedzki.hotel.repository.ReservationRepository;
import pl.biedzki.hotel.repository.RoomRepository;

@RestController
@RequestMapping(ResourceConstants.ROOM_RESERVATION_V1)
@CrossOrigin
public class ReservationResource {

	@Autowired
	PageableRoomRepository pageableRoomRepository;

	@Autowired
	RoomRepository roomRepository;

	@Autowired
	RoomEntityToReservableRoomResponseConverter converter;

	@Autowired
	ReservationRepository reservationRepository;

	@Autowired
	ConversionService conversionService;

	@RequestMapping(path = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Page<ReservableRoomResponse> getAvailableRooms(
			@RequestParam(value = "checkin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkin,
			@RequestParam(value = "checkout") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkout,
			Pageable pageable) {

		Page<RoomEntity> roomEntityList = pageableRoomRepository.findAll(pageable);

		return roomEntityList.map(converter::convert);

	}

	@RequestMapping(path = "/{roomId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<RoomEntity> getRoomById(@PathVariable Long roomId) {

		RoomEntity roomEntity = roomRepository.findById(roomId).get();
		return new ResponseEntity<>(roomEntity, HttpStatus.OK);
	}

	@RequestMapping(path = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ReservationResponse> createReservation(@RequestBody ReservationRequest reservationRequest) {

		ReservationEntity reservationEntity = conversionService.convert(reservationRequest, ReservationEntity.class);
		reservationRepository.save(reservationEntity);

		RoomEntity roomEntity = roomRepository.findById(reservationRequest.getRoomId()).get();
		roomEntity.addReservationEntity(reservationEntity);
		reservationEntity.setRoomEntity(roomEntity);
		roomRepository.save(roomEntity);


		//Control block of databases
		System.out.println("-------------------------------------------------------------------");
		Iterable<ReservationEntity> reservations = reservationRepository.findAll();
		System.out.println("There are the following reservations in H2 database :");
		for (ReservationEntity reservation : reservations) {
			System.out.println(reservation);
		}
		System.out.println("-------------------------------------------------------------------");
		Iterable<RoomEntity> rooms = roomRepository.findAll();
		System.out.println("There are the following rooms in H2 database :");
		for (RoomEntity room : rooms) {
			System.out.println(room);
		}

		ReservationResponse reservationResponse = conversionService.convert(reservationEntity,
				ReservationResponse.class);

		return new ResponseEntity<>(reservationResponse, HttpStatus.CREATED);
	}

	@RequestMapping(path = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ReservableRoomResponse> updateReservation(
			@RequestBody ReservationRequest reservationRequest) {

		return new ResponseEntity<>(new ReservableRoomResponse(), HttpStatus.OK);
	}

	@RequestMapping(path = "/{reservationId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteReservation(@PathVariable long reservationId) {

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
