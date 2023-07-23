package com.example.Parche.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Asistente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double gasto;

    private Double debe;

    @ManyToOne
    @JoinColumn(name = "parche_id",referencedColumnName = "id")
    @JsonIgnore
    private Parche parche;

    private Integer dias;

    // Constructor, getters y setters
}