package com.aston.intensive.hibernate.astonintensivehibernate.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
@Entity
@Table(name = "item_attribute")
public class ItemAttribute {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    Long id;

    @Column(name = "name", nullable = false, unique = true)
    String name;

    @ManyToMany(mappedBy = "attributes")
    Set<Item> items = new HashSet<>();

    @ManyToMany(cascade = ALL)
    @JoinTable(
            name = "item_item_attribute",
            joinColumns = @JoinColumn(name = "item_attribute_id"),
            inverseJoinColumns = @JoinColumn(name = "item_attribute_value_id")
    )
    Set<ItemAttributeValue> attributeValues = new HashSet<>();
}
