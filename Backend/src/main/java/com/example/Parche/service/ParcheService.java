package com.example.Parche.service;

import com.example.Parche.entity.Parche;
import com.example.Parche.repository.ParcheRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParcheService {
    @Autowired
    private ParcheRepository parcheRepository;

    public List<Parche> getAllParches() {
        return parcheRepository.findAll();
    }

    public Parche getParcheById(Long id) {
        return parcheRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Parche no encontrado"));
    }

    public Parche createParche(Parche parche) {
        return parcheRepository.save(parche);
    }

    public Parche updateParche(Long id, Parche parcheDetails) {
        Parche parche = getParcheById(id);
        parche.setNombre(parcheDetails.getNombre());
        parche.setFecha(parcheDetails.getFecha());
        parche.setDias(parcheDetails.getDias());
        parche.setGastoTotal(parcheDetails.getGastoTotal());
        return parcheRepository.save(parche);
    }

    public void deleteParche(Long id) {
        parcheRepository.deleteById(id);
    }
}