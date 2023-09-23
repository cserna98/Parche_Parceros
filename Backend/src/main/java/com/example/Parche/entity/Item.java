package com.example.Parche.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String descripcion;


    private Integer dias;

    private Date dia;

    private String img;

    private Double costo;

    @ManyToOne
    @JoinColumn(name = "parche_id",referencedColumnName = "id")
    private Parche parche;

    @ManyToOne
    @JoinColumn(name = "asistente_id",referencedColumnName = "id")
    private Asistente asistente;

    @ManyToMany
    @JoinTable(
            name = "item_no_consume",
            joinColumns = @JoinColumn(name = "item_id"),
            inverseJoinColumns = @JoinColumn(name = "asistente_id")
    )
    private Set<Asistente> NoConsume = new HashSet<>();


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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getDias() {
        return dias;
    }

    public Date getDia() {
        return dia;
    }

    public void setDia(Date dia) {
        this.dia = dia;
    }

    public void setDias(Integer dias) {
        this.dias = dias;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public Parche getParche() {
        return parche;
    }

    public void setParche(Parche parche) {
        this.parche = parche;
    }

    public Asistente getAsistente() {
        return asistente;
    }

    public void setAsistente(Asistente asistente) {
        this.asistente = asistente;
    }

    public Set<Asistente> getNoConsume() {
        return NoConsume;
    }

    public void setNoConsume(Set<Asistente> noConsume) {
        NoConsume = noConsume;
    }
}