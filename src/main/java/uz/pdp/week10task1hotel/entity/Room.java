package uz.pdp.week10task1hotel.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Integer number;

    @Column
    private Integer floor;

    @Column
    private Double size;

    @ManyToOne
    private Hotel hotel;
}
