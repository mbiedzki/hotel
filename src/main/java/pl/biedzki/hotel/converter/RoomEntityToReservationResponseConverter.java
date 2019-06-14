 package pl.biedzki.hotel.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import pl.biedzki.hotel.entity.RoomEntity;
import pl.biedzki.hotel.model.Links;
import pl.biedzki.hotel.model.Self;
import pl.biedzki.hotel.model.response.ReservationResponse;
import pl.biedzki.hotel.rest.ResourceConstants;

@Component
public class RoomEntityToReservationResponseConverter implements Converter<RoomEntity, ReservationResponse> {

	@Override
	public ReservationResponse convert(RoomEntity source) {
		// TODO Auto-generated method stub
		
		ReservationResponse reservationResponse = new ReservationResponse();
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
 