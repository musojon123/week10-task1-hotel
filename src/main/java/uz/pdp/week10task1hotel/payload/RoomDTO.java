package uz.pdp.week10task1hotel.payload;

import lombok.Data;

@Data
public class RoomDTO {
    private Integer number;
    private Integer floor;
    private Double size;
    private Integer hotelId;
}
