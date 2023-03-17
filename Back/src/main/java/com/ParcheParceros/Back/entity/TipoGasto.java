package com.ParcheParceros.Back.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TipoGasto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String descripcion;

    // Aquí podrías agregar más atributos, como una imagen o cualquier otra información adicional

    @OneToMany(mappedBy = "tipoGasto")
    private List<Item> items;

    // Constructor omitido
}
