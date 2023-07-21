package com.example.Parche.entity;

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
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String descripcion;

    private Integer dia;

    private String img;

    private Double costo;

    @ManyToOne
    @JoinColumn(name = "parche_id")
    private Parche parche;

    @ManyToOne
    @JoinColumn(name = "asistente_id")
    private Asistente asistente;


}