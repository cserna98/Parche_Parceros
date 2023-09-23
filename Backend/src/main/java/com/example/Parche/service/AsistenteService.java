package com.example.Parche.service;

import com.example.Parche.DTO.AsistenteDTO;
import com.example.Parche.entity.Asistente;
import com.example.Parche.entity.Parche;
import com.example.Parche.entity.usuario.Usuario;
import com.example.Parche.repository.AsistenteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AsistenteService {
    @Autowired
    private AsistenteRepository asistenteRepository;

    @Autowired
    private UsuarioService usuarioservice;
    @Autowired
    private ParcheService parcheService;

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
        List<Asistente> asistentes = asistenteRepository.findByNombre(nombre);

        if (asistentes.isEmpty()) {
            throw new EntityNotFoundException("Asistente no encontrado con nombre: " + nombre);
        }

        // Suponiendo que solo quieres el primer asistente encontrado, puedes devolverlo
        return asistentes.get(0);
    }


    public Asistente createAsistente(Asistente asistente, Long idParche) {
            // Obtener el parche existente por su ID
            Parche parche = parcheService.getParcheById(idParche);

            // Verificar si el usuario con el mismo correo electrónico ya existe
            Optional<Usuario> usuarioExistente = usuarioservice.getUserByEmail(asistente.getEmail());

            if (usuarioExistente.isPresent()) {
                // Si el usuario ya existe, puedes asignarlo como asistente al parche
                Asistente asistenteExistente = (Asistente) usuarioExistente.get();
                asistenteExistente.setParche(parche);

                // Puedes realizar otras operaciones relacionadas con el asistente existente si es necesario

                // Guardar el asistente existente actualizado en la base de datos
                asistenteRepository.save(asistenteExistente);

                return asistenteExistente;
            } else {
                // Si el usuario no existe, primero asigna el parche al asistente
                throw new EntityNotFoundException("Asistente no encontrado con nombre: " + nombre);

                // Puedes realizar otras operaciones relacionadas con el asistente si es necesario

                // Guardar el nuevo asistente en la base de datos
                return asistenteRepository.save(asistente);
            }
    }



    public Asistente updateAsistente(Long id, Asistente usuarioDetails) {
        Asistente usuario = getAsistenteById(id);
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
