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

    @ManyToOne
    @JoinColumn(name = "tipo_gasto_id")
    private TipoGasto tipoGasto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parche_id")
    private Parche parche;

    // Constructor omitido
}