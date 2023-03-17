package com.ParcheParceros.Back.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private Double valor;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parche_id")
    private Parche parche;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "asistente_id")
    private Asistente asistente;


    // Constructor omitido
}