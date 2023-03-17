package com.ParcheParceros.Back.service;

import com.ParcheParceros.Back.entity.Parche;
import com.ParcheParceros.Back.repository.ParcheRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ParcheService {

    private final ParcheRepository parcheRepository;

    public ParcheService(ParcheRepository parcheRepository) {
        this.parcheRepository = parcheRepository;
    }

    public Parche crearParche(Parche parche) {
        return parcheRepository.save(parche);
    }

    public Parche obtenerParchePorId(Long id) {
        return parcheRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No se encontró el parche con el ID: " + id));
    }

    public List<Parche> obtenerTodosLosParches() {
        return parcheRepository.findAll();
    }

    public void eliminarParche(Parche parche) {
        parcheRepository.delete(parche);
    }

}
