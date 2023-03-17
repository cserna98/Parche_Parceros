package com.ParcheParceros.Back.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Asistente extends Usuario {

    private Double deuda;

    @OneToMany(mappedBy = "asistente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> items = new ArrayList<>();

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "parche_id")
    private Parche parche;

}