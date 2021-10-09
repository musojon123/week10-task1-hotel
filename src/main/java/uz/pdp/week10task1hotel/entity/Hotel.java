package uz.pdp.week10task1hotel.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;
}
