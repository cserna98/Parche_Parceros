package com.example.Parche.controller;
import com.example.Parche.entity.Parche;
import com.example.Parche.service.ParcheService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parches")
public class ParcheController {

    private final ParcheService parcheService;

    public ParcheController(ParcheService parcheService) {
        this.parcheService = parcheService;
    }

    @GetMapping
    public List<Parche> getAllParches() {
        return parcheService.getAllParches();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Parche> getParcheById(@PathVariable Long id) {
        Parche parche = parcheService.getParcheById(id);
        return ResponseEntity.ok(parche);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Parche createParche(@RequestBody Parche parche) {
        return parcheService.createParche(parche);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Parche> updateParche(@PathVariable Long id, @RequestBody Parche parcheDetails) {
        Parche updatedParche = parcheService.updateParche(id, parcheDetails);
        return ResponseEntity.ok(updatedParche);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteParche(@PathVariable Long id) {
        parcheService.deleteParche(id);
    }
}