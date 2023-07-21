package com.example.Parche.service;

import com.example.Parche.entity.Asistente;
import com.example.Parche.repository.AsistenteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AsistenteService {
    @Autowired
    private AsistenteRepository asistenteRepository;

    public List<Asistente> getAllAsistentes() {
        return asistenteRepository.findAll();
    }

    public Asistente getAsistenteById(Long id) {
        return asistenteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
    }

    public Asistente createAsistente(Asistente asistente) {
        return asistenteRepository.save(asistente);
    }

    public Asistente updateAsistente(Long id, Asistente usuarioDetails) {
        Asistente usuario = getAsistenteById(id);
        usuario.setName(usuarioDetails.getName());
        usuario.setGasto(usuarioDetails.getGasto());
        usuario.setDebe(usuarioDetails.getDebe());
        usuario.setParche(usuarioDetails.getParche());
        usuario.setDias(usuarioDetails.getDias());
        return asistenteRepository.save(usuario);
    }

    public void deleteAsistente(Long id) {
        asistenteRepository.deleteById(id);
    }
}
