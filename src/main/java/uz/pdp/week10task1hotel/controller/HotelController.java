package uz.pdp.week10task1hotel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.week10task1hotel.entity.Hotel;
import uz.pdp.week10task1hotel.repository.HotelRepository;

import java.util.List;

@RestController
@RequestMapping("/hotel")
public class HotelController {
    @Autowired
    HotelRepository hotelRepository;
    @PostMapping
    private String addHotel(@RequestBody Hotel hotel){
        Hotel addingHotel = new Hotel();
        addingHotel.setName(hotel.getName());
        hotelRepository.save(addingHotel);
        return "Hotel added";
    }

    @GetMapping
    private List<Hotel> getHotels(){
        return hotelRepository.findAll();
    }

    @GetMapping("/{id}")
    private Hotel getHotel(@PathVariable Integer id){
        if (hotelRepository.existsById(id)){
            return hotelRepository.getById(id);
        }
        return new Hotel();
    }

    @PutMapping("/{id}")
    private String editHotel(@PathVariable Integer id, @RequestBody Hotel hotel){
        if (hotelRepository.existsById(id)){
            Hotel editingHotel = hotelRepository.getById(id);
            editingHotel.setName(hotel.getName());
            hotelRepository.save(editingHotel);
            return "Hotel edited";
        }
        return "No such hotel";
    }

    @DeleteMapping("/{id}")
    private String deleteHotel(@PathVariable Integer id){
        if (hotelRepository.existsById(id)){
            hotelRepository.deleteById(id);
            return "Hotel deleted";
        }
        return "No such Hotel";
    }

}
