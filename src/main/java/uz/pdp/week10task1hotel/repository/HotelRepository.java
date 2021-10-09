package uz.pdp.week10task1hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.week10task1hotel.entity.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Integer> {
}
