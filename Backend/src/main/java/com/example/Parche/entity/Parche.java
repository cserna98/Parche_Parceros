package com.example.Parche.entity;

import com.example.Parche.entity.usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

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
    private Set<Asistente> asistentes = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "usuario_parche",
            joinColumns = @JoinColumn(name = "parche_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id")
    )
    private Set<Usuario> usuarios;

    @OneToMany(mappedBy = "parche", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<Item> items;


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

    public Set<Asistente> getAsistentes() {
        return asistentes;
    }

    public void setAsistentes(Set<Asistente> asistentes) {
        this.asistentes = asistentes;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }
}