package com.example.Parche.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {
    private Long id;
    private String name;
    private String descripcion;
    private Integer dia;
    private String img;
    private Double costo;
    private String nombreParche;
    private String nombreAsistente;

    // Constructor, getters y setters
}
