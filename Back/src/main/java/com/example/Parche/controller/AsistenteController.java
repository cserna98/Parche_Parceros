package com.example.Parche.controller;

import com.example.Parche.entity.Asistente;
import com.example.Parche.service.AsistenteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/asistentes")
public class AsistenteController {

    private final AsistenteService asistenteService;

    public AsistenteController(AsistenteService asistenteService) {
        this.asistenteService = asistenteService;
    }

    @GetMapping
    public List<Asistente> getAllAsistentes() {
        return asistenteService.getAllAsistentes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Asistente> getAsistenteById(@PathVariable Long id) {
        Asistente asistente = asistenteService.getAsistenteById(id);
        return ResponseEntity.ok(asistente);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Asistente createAsistente(@RequestBody Asistente asistente) {
        return asistenteService.createAsistente(asistente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Asistente> updateAsistente(@PathVariable Long id, @RequestBody Asistente asistenteDetails) {
        Asistente updatedAsistente = asistenteService.updateAsistente(id, asistenteDetails);
        return ResponseEntity.ok(updatedAsistente);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAsistente(@PathVariable Long id) {
        asistenteService.deleteAsistente(id);
    }
}
