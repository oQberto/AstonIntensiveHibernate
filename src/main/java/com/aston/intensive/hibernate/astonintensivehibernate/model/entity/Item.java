package com.aston.intensive.hibernate.astonintensivehibernate.model.entity;

import com.aston.intensive.hibernate.astonintensivehibernate.model.enums.Type;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    Long id;

    @Column(name = "name", nullable = false, unique = true)
    String name;

    @Enumerated(STRING)
    @Column(name = "type", nullable = false)
    Type type;

    @Column(name = "price", nullable = false)
    BigDecimal price;

    @Column(name = "quantity", nullable = false)
    Integer quantity;

    @Column(name = "discount", nullable = false)
    Double discount;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "manufacturer_id")
    Manufacturer manufacturer;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "country_of_origin_id")
    CountryOfOrigin countryOfOrigin;

    @ManyToMany(cascade = ALL)
    @JoinTable(
            name = "item_item_attribute",
            joinColumns = @JoinColumn(name = "item_id"),
            inverseJoinColumns = @JoinColumn(name = "item_attribute_id")
    )
    Set<ItemAttribute> attributes = new HashSet<>();

    @ManyToMany(cascade = ALL)
    @JoinTable(
            name = "item_order",
            joinColumns = @JoinColumn(name = "item_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id")
    )
    List<Order> orders = new ArrayList<>();
}
