package com.example.Parche.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AsistenteDTO {

    private Long id;
    private String name;
    private Integer dias;
    private Long idParche;

    // Constructor, getters, and setters (if needed)
}