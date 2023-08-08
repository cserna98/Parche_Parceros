package com.example.Parche.controller;
import com.example.Parche.DTO.ParcheDTO;
import com.example.Parche.entity.Asistente;
import com.example.Parche.entity.Item;
import com.example.Parche.entity.Parche;
import com.example.Parche.service.AsistenteService;
import com.example.Parche.service.ItemService;
import com.example.Parche.service.ParcheService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/parches")
public class ParcheController {

    private final ParcheService parcheService;
    private final ItemService itemService;
    private final AsistenteService asistenteService;
    private final ModelMapper modelMapper;


    public ParcheController(ParcheService parcheService, ItemService itemService, AsistenteService asistenteService, ModelMapper modelMapper) {
        this.parcheService = parcheService;
        this.itemService = itemService;
        this.asistenteService = asistenteService;
        this.modelMapper = modelMapper;

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
    public ResponseEntity<Parche> createParche(@RequestBody ParcheDTO parcheDTO) {
        Parche parche = modelMapper.map(parcheDTO, Parche.class);
        Parche createdParche = parcheService.createParche(parche);
        return ResponseEntity.ok(createdParche);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Parche> updateParche(@PathVariable Long id, @RequestBody ParcheDTO parcheDTODetails) {
        Parche parche = modelMapper.map(parcheDTODetails, Parche.class);

        // Convertir los nombres de items en objetos Item y agregarlos al parche

        Parche updateParche = parcheService.updateParche(id,parche);
        return ResponseEntity.ok(updateParche);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteParche(@PathVariable Long id) {
        parcheService.deleteParche(id);
    }


}