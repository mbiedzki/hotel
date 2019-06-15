package pl.biedzki.hotel.repository;

import org.springframework.data.repository.CrudRepository;

import pl.biedzki.hotel.entity.ReservationEntity;

public interface ReservationRepository extends CrudRepository<ReservationEntity, Long> {
	

}
