package com.example.Parche.service;

import com.example.Parche.DTO.AsistenteDTO;
import com.example.Parche.entity.Asistente;
import com.example.Parche.repository.AsistenteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AsistenteService {
    @Autowired
    private AsistenteRepository asistenteRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<Asistente> getAllAsistentes() {
        return asistenteRepository.findAll();
    }

    public Asistente getAsistenteById(Long id) {
        return asistenteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
    }

    public List<AsistenteDTO> getAsistentesByParcheId(Long parcheId) {
        List<Asistente> asistentes = asistenteRepository.findByParcheId(parcheId);

        // Convertir objetos Asistente a objetos AsistenteDTO usando ModelMapper
        List<AsistenteDTO> asistentesDTO = asistentes.stream()
                .map(asistente -> modelMapper.map(asistente, AsistenteDTO.class))
                .collect(Collectors.toList());

        return asistentesDTO;
    }
    public Asistente findByName(String nombre) {
        return asistenteRepository.findByNombre(nombre).orElseThrow(() -> new EntityNotFoundException("Asistente no encontrado con nombre: " + nombre));
    }
    public Asistente createAsistente(Asistente asistente) {
        return asistenteRepository.save(asistente);
    }

    public Asistente updateAsistente(Long id, Asistente usuarioDetails) {
        Asistente usuario = getAsistenteById(id);
        usuario.setNombre(usuarioDetails.getNombre());
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
