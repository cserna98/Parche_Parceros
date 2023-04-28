package com.ParcheParceros.Back.service;

import com.ParcheParceros.Back.entity.Asistente;
import com.ParcheParceros.Back.entity.Item;
import com.ParcheParceros.Back.repository.AsistenteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AsistenteService {

    private final AsistenteRepository asistenteRepository;

    public AsistenteService(AsistenteRepository asistenteRepository) {
        this.asistenteRepository = asistenteRepository;
    }

    public Asistente crearAsistente(Asistente asistente) {
        return asistenteRepository.save(asistente);
    }

    public Asistente obtenerAsistentePorId(Long id) {
        return asistenteRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No se encontró el asistente con el ID: " + id));
    }

    public List<Asistente> obtenerTodosLosAsistentes() {
        return asistenteRepository.findAll();
    }

    public void eliminarAsistente(Asistente asistente) {
        asistenteRepository.delete(asistente);
    }

    public Double CalcularGasto (Asistente asistente) {
        Double SumaItems = asistente.getItems().stream().mapToDouble(Item::getValor).sum();
        return SumaItems;
    }

}
