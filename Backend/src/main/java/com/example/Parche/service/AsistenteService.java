package com.example.Parche.service;

import com.example.Parche.DTO.AsistenteDTO;
import com.example.Parche.entity.Asistente;
import com.example.Parche.entity.Parche;
import com.example.Parche.entity.usuario.Usuario;
import com.example.Parche.repository.AsistenteRepository;
import com.example.Parche.repository.UsuarioRepository;
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


    public Asistente createAsistente(AsistenteDTO asistenteDTO, Long idParche) {
        System.out.println("Iniciando creación de asistente...");


            // Verificar si el usuario existe
            Optional<Usuario> usuarioExistenteOptional = usuarioservice.getUserByEmail(asistenteDTO.getEmail());

            if (usuarioExistenteOptional.isPresent()) {
                System.out.println("Usuario existe " + usuarioExistenteOptional);
                // Si el usuario ya existe, asignar ese usuario al asistente en lugar de crear uno nuevo
                Usuario usuarioExistente = usuarioExistenteOptional.get();
                System.out.println("Usuario existe " + usuarioExistente);

                // Convertir AsistenteDTO a Asistente utilizando ModelMapper
                Asistente nuevoAsistente = modelMapper.map(asistenteDTO, Asistente.class);

                System.out.println("Nuevo asistente: " + nuevoAsistente);

                // Obtener el parche por ID y asignarlo al asistente
                Parche parche = parcheService.getParcheById(idParche);
                System.out.println("Parche existente: " + idParche);
                System.out.println(parche);
                nuevoAsistente.setParche(parche);
                nuevoAsistente.setUser(true);

                // Agregar el parche a la lista de parches del usuario
                usuarioExistente.getParches().add(parche);

                // Asignar el usuario al asistente
                nuevoAsistente.setUsuario(usuarioExistente);
                nuevoAsistente.setNombre(usuarioExistente.getFirstname() + " " +  usuarioExistente.getLastname());
                System.out.println("new asistente: " + nuevoAsistente);
                // Guardar el nuevo asistente y actualizar el usuario
                asistenteRepository.save(nuevoAsistente);
                usuarioservice.actualizarUsuario(usuarioExistente.getId(), usuarioExistente);
                System.out.println("Asistente guardado: " + nuevoAsistente);

                return nuevoAsistente;
            } else {
                // Si el usuario no existe, puedes manejarlo según tus necesidades
                Asistente nuevoAsistente = modelMapper.map(asistenteDTO, Asistente.class);
                Parche parche = parcheService.getParcheById(idParche);
                nuevoAsistente.setParche(parche);
                nuevoAsistente.setUser(false);
                asistenteRepository.save(nuevoAsistente);
                throw new EntityNotFoundException("Usuario no encontrado con el correo: " + asistenteDTO.getEmail());
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
