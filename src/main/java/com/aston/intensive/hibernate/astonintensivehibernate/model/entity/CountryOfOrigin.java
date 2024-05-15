package com.aston.intensive.hibernate.astonintensivehibernate.model.entity;

import com.aston.intensive.hibernate.astonintensivehibernate.model.enums.CountryCode;
import com.aston.intensive.hibernate.astonintensivehibernate.model.enums.CountryName;
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
@Table(name = "country_of_origin")
public class CountryOfOrigin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Enumerated(STRING)
    @Column(name = "short_name", nullable = false, unique = true)
    CountryCode countryCode;

    @Enumerated(STRING)
    @Column(name = "full_name", nullable = false, unique = true)
    CountryName countryName;

    @OneToMany(mappedBy = "countryOfOrigin")
    List<Item> items = new ArrayList<>();
}
