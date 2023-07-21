package com.example.Parche.entity;

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

    private String nombre;

    private String fecha;

    private Integer dias;

    private Double gastoTotal;

    @OneToMany(mappedBy = "parche", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Asistente> usuarios;

    @OneToMany(mappedBy = "parche", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> items;

    // Constructor, getters y setters
}