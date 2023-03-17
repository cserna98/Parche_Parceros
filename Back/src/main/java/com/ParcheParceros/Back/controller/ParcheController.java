package com.ParcheParceros.Back.controller;

import com.ParcheParceros.Back.entity.Parche;
import com.ParcheParceros.Back.service.ParcheService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/parches")
public class ParcheController {

    private final ParcheService parcheService;

    public ParcheController(ParcheService parcheService) {
        this.parcheService = parcheService;
    }

    @PostMapping
    public ResponseEntity<Parche> crearParche(@RequestBody Parche parche) {
        Parche parcheCreado = parcheService.crearParche(parche);
        return ResponseEntity.created(URI.create("/parches/" + parcheCreado.getId())).body(parcheCreado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Parche> obtenerParchePorId(@PathVariable Long id) {
        Parche parche = parcheService.obtenerParchePorId(id);
        return ResponseEntity.ok(parche);
    }

    @GetMapping
    public ResponseEntity<List<Parche>> obtenerTodosLosParches() {
        List<Parche> parches = parcheService.obtenerTodosLosParches();
        return ResponseEntity.ok(parches);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarParche(@PathVariable Long id) {
        Parche parche = parcheService.obtenerParchePorId(id);
        parcheService.eliminarParche(parche);
        return ResponseEntity.noContent().build();
    }

}