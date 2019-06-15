package pl.biedzki.hotel.converter;

import org.springframework.core.convert.converter.Converter;

import pl.biedzki.hotel.entity.ReservationEntity;
import pl.biedzki.hotel.model.response.ReservationResponse;

public class ReservationEntityToReservationResponseConverter implements Converter<ReservationEntity, ReservationResponse>{

	@Override
	public ReservationResponse convert(ReservationEntity source) {
		
		ReservationResponse reservationResponse = new ReservationResponse();
		reservationResponse.setCheckin(source.getCheckin());
		reservationResponse.setCheckout(source.getCheckout());
		
		if(null != source.getRoomEntity()) reservationResponse.setId(source.getRoomEntity().getId());
		
		return reservationResponse;
	}

	
}
