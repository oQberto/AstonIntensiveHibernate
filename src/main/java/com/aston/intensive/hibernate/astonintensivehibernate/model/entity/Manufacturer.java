package com.aston.intensive.hibernate.astonintensivehibernate.model.entity;

import com.aston.intensive.hibernate.astonintensivehibernate.model.enums.ManufacturerName;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.EnumType.STRING;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Manufacturer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Enumerated(STRING)
    @Column(name = "name", nullable = false, unique = true)
    ManufacturerName manufacturerName;

    @OneToMany(mappedBy = "manufacturer")
    List<Item> items = new ArrayList<>();
}
