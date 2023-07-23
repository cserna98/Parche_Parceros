package com.example.Parche.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Parche {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String fecha;

    private Integer dias;

    private Double GastoTotal;

    @OneToMany(mappedBy = "parche", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Asistente> Asistente;

    @OneToMany(mappedBy = "parche", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Item> items;

    // Constructor, getters y setters
}