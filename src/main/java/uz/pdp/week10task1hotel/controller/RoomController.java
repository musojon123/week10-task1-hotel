package uz.pdp.week10task1hotel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import uz.pdp.week10task1hotel.entity.Hotel;
import uz.pdp.week10task1hotel.entity.Room;
import uz.pdp.week10task1hotel.payload.RoomDTO;
import uz.pdp.week10task1hotel.repository.HotelRepository;
import uz.pdp.week10task1hotel.repository.RoomRepository;

import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomController {
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    HotelRepository hotelRepository;

    @PostMapping
    public String addRoom(@RequestBody RoomDTO roomDTO){
        Room room = new Room();
        if (!hotelRepository.existsById(roomDTO.getHotelId())){
            return "No such hotel found";
        }

        room.setNumber(roomDTO.getNumber());
        room.setFloor(roomDTO.getFloor());
        room.setSize(roomDTO.getSize());
        room.setHotel(hotelRepository.getById(roomDTO.getHotelId()));
        roomRepository.save(room);
        return "Room added";
    }

    @GetMapping
    public List<Room> getAllRooms(){
        return roomRepository.findAll();
    }

    @GetMapping("/{id}")
    public Room getRoomById(@PathVariable Integer id){
        if (roomRepository.existsById(id)){
            return roomRepository.getById(id);
        }
        return new Room();
    }

    @GetMapping("/hotel/{hotelId}")
    public Page<Room> getAllRoomByHotelId(@PathVariable Integer id, @RequestParam int page){
        Pageable pageable = PageRequest.of(page, 10     );
        return roomRepository.findAllByHotelId(id, pageable);
    }

    @PutMapping("/{id}")
    public String editRoom(@PathVariable Integer id, @RequestBody RoomDTO roomDTO){
        if (!roomRepository.existsById(id))
            return "No such room exist";
        if (!hotelRepository.existsById(roomDTO.getHotelId()))
            return "No such hotel exist";

        Room editingRoom = roomRepository.getById(id);
        editingRoom.setNumber(roomDTO.getNumber());
        editingRoom.setFloor(roomDTO.getFloor());
        editingRoom.setSize(roomDTO.getSize());
        editingRoom.setHotel(hotelRepository.getById(roomDTO.getHotelId()));
        roomRepository.save(editingRoom);
        return "Edited ";
    }

    @DeleteMapping("/{id}")
    public String deleteRoom(@PathVariable Integer id){
        if (roomRepository.existsById(id))
            return "No such room exist";

        roomRepository.deleteById(id);
        return "Deleted";
    }
}
