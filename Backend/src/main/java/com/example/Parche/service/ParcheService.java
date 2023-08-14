package com.example.Parche.service;

import com.example.Parche.entity.Item;
import com.example.Parche.entity.Parche;
import com.example.Parche.repository.ParcheRepository;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParcheService {
    @Autowired
    private ParcheRepository parcheRepository;
    private Parche parche;


    public List<Parche> getAllParches() {
        return parcheRepository.findAll();
    }

    public Parche getParcheById(Long id) {
        return parcheRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Parche no encontrado"));
    }

    public Parche getParcheByName(String nombre) {
        return parcheRepository.findByNombre(nombre)
                .orElseThrow(() -> new EntityNotFoundException("Parche no encontrado con el nombre: " + nombre));
    }



    public Parche createParche(Parche parche) {
        return parcheRepository.save(parche);
    }

    public Parche updateParche(Long id, Parche parcheDetails) {
        Parche parche = getParcheById(id);
        parche.setNombre(parcheDetails.getNombre());
        parche.setFechaInicio(parcheDetails.getFechaInicio());
        parche.setFechaFin(parcheDetails.getFechaFin());
        parche.setDias(parcheDetails.getDias());
        parche.setGastoTotal(parcheDetails.getGastoTotal());
        return parcheRepository.save(parche);
    }

    public void deleteParche(Long id) {
        parcheRepository.deleteById(id);
    }

        public void TotalCost() {
        List<Parche> parches = parcheRepository.findAll();
        for (Parche parche : parches) {
            double gastoTotal = 0.0;
            for (Item item : parche.getItems()) {
                gastoTotal += item.getCosto();
            }
            parche.setGastoTotal(gastoTotal);
            parcheRepository.save(parche);
        }
    }


}

