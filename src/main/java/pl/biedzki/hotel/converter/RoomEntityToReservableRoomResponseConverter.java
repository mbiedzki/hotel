 package pl.biedzki.hotel.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import pl.biedzki.hotel.entity.RoomEntity;
import pl.biedzki.hotel.model.Links;
import pl.biedzki.hotel.model.Self;
import pl.biedzki.hotel.model.response.ReservableRoomResponse;
import pl.biedzki.hotel.rest.ResourceConstants;

@Component
public class RoomEntityToReservableRoomResponseConverter implements Converter<RoomEntity, ReservableRoomResponse> {

	@Override
	public ReservableRoomResponse convert(RoomEntity source) {
	 
		
		ReservableRoomResponse reservationResponse = new ReservableRoomResponse();
		reservationResponse.setId(source.getId());
		reservationResponse.setRoomNumber(source.getRoomNumber());
		reservationResponse.setPrice(Integer.valueOf(source.getPrice()));
		
		Links links = new Links();
		Self self = new Self();
		self.setRef(ResourceConstants.ROOM_RESERVATION_V1 + "/" + source.getId());
		links.setSelf(self);
		
		reservationResponse.setLinks(links);
		
		return reservationResponse;
	}

}
 