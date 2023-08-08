package com.example.Parche.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Parche {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFin;


    private Integer dias;

    private Double GastoTotal;

    @OneToMany(mappedBy = "parche", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Asistente> Asistente;

    @OneToMany(mappedBy = "parche", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Item> items;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
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

    public List<com.example.Parche.entity.Asistente> getAsistente() {
        return Asistente;
    }

    public void setAsistente(List<com.example.Parche.entity.Asistente> asistente) {
        Asistente = asistente;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}