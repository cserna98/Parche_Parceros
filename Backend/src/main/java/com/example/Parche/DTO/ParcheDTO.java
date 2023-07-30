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
    private Double GastoTotal;
    private List<String> nombresAsistentes;
    private List<String> nombresItems;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Integer getDias() {
        return dias;
    }

    public void setDias(Integer dias) {
        this.dias = dias;
    }

    public Double getGastoTotal() {
        return GastoTotal;
    }

    public void setGastoTotal(Double gastoTotal) {
        GastoTotal = gastoTotal;
    }

    public List<String> getNombresAsistentes() {
        return nombresAsistentes;
    }

    public void setNombresAsistentes(List<String> nombresAsistentes) {
        this.nombresAsistentes = nombresAsistentes;
    }

    public List<String> getNombresItems() {
        return nombresItems;
    }

    public void setNombresItems(List<String> nombresItems) {
        this.nombresItems = nombresItems;
    }
}
