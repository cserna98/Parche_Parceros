package com.example.Parche.controller;

import com.example.Parche.DTO.AsistenteDTO;
import com.example.Parche.entity.Asistente;
import com.example.Parche.service.AsistenteService;
import com.example.Parche.service.ParcheService;
import jakarta.persistence.EntityNotFoundException;
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

    @PostMapping("/{idParche}/parche")
    public ResponseEntity<String> crearAsistente(
            @PathVariable Long idParche,
            @RequestBody AsistenteDTO asistenteDTO
    ) {
        try {
            Asistente asistenteCreado = asistenteService.createAsistente(asistenteDTO, idParche);
            return ResponseEntity.ok("Asistente creado exitosamente.");
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear el asistente.");
        }
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
