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
    private String nombre;
    private Integer dias;
    private Long idParche;

    // Constructor, getters, and setters (if needed)


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return nombre;
    }

    public void setName(String name) {
        this.nombre= name;
    }

    public Integer getDias() {
        return dias;
    }

    public void setDias(Integer dias) {
        this.dias = dias;
    }

    public Long getIdParche() {
        return idParche;
    }

    public void setIdParche(Long idParche) {
        this.idParche = idParche;
    }
}