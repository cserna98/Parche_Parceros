package com.example.Parche.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ParcheDTO {
    private String nombre;
    private String fecha;
    private Integer dias;
    private List<String> nombresAsistentes;
    private List<String> nombresItems;

}
