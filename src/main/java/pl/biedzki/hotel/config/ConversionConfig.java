package pl.biedzki.hotel.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;

import pl.biedzki.hotel.converter.ReservationEntityToReservationResponseConverter;
import pl.biedzki.hotel.converter.ReservationRequestToReservationEntityConverter;
import pl.biedzki.hotel.converter.RoomEntityToReservableRoomResponseConverter;

@Configuration
public class ConversionConfig {
	
	private Set<Converter> getConverters() {
		Set<Converter> converters = new HashSet<Converter>();
		converters.add(new RoomEntityToReservableRoomResponseConverter());
		converters.add(new ReservationEntityToReservationResponseConverter());
		converters.add(new ReservationRequestToReservationEntityConverter());
		
		return converters;
	}
	
	@Bean
	public ConversionService conversionService() {
		ConversionServiceFactoryBean bean = new ConversionServiceFactoryBean();
		bean.setConverters(getConverters());
		bean.afterPropertiesSet();
		
		return bean.getObject();
	}

}
