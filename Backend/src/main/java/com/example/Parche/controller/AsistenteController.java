package com.example.Parche.controller;

import com.example.Parche.DTO.AsistenteDTO;
import com.example.Parche.entity.Asistente;
import com.example.Parche.service.AsistenteService;
import com.example.Parche.service.ParcheService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/asistentes")
public class AsistenteController {

    private final AsistenteService asistenteService;
    private final ModelMapper modelMapper;

    private final ParcheService parcheService;

    public AsistenteController(AsistenteService asistenteService, ModelMapper modelMapper, ParcheService parcheService) {
        this.asistenteService = asistenteService;
        this.modelMapper = modelMapper;
        this.parcheService = parcheService;
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

    @GetMapping("/{parcheId}/asistentes")
    public ResponseEntity<List<AsistenteDTO>> getAsistentesByParcheId(@PathVariable Long parcheId) {
        List<AsistenteDTO> asistentes = asistenteService.getAsistentesByParcheId(parcheId);
        return ResponseEntity.ok(asistentes);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Asistente createAsistente(@RequestBody AsistenteDTO asistenteDTO) {
        Asistente asistente = modelMapper.map(asistenteDTO, Asistente.class);
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
