package pl.biedzki.hotel.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import pl.biedzki.hotel.entity.RoomEntity;

public interface RoomRepository extends CrudRepository<RoomEntity, Long> {

	List<RoomEntity> findAll();



}
