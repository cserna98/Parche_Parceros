package com.example.Parche.service;

import com.example.Parche.entity.usuario.Usuario;
import com.example.Parche.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;


    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, BCryptPasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> getUserById(Long id) {
        return usuarioRepository.findById(id);
    }



    public Optional<Usuario> getUserByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }
    public Usuario crearUsuario(Usuario usuario) {
        // Codifica la contraseña antes de guardarla en la base de datos
        System.out.println("usuario es" + usuario);
        String passwordEncriptada = passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(passwordEncriptada);
        return usuarioRepository.save(usuario);
    }

    public Usuario actualizarUsuario(Long id, Usuario usuario) {
        // Verifica si el usuario con el ID proporcionado existe
        Optional<Usuario> usuarioExistente = usuarioRepository.findById(id);
        if (usuarioExistente.isPresent()) {
            // Actualiza los campos del usuario existente con los valores del nuevo usuario
            Usuario usuarioActualizado = usuarioExistente.get();
            usuarioActualizado.setFirstname(usuario.getFirstname());
            usuarioActualizado.setLastname(usuario.getLastname());
            usuarioActualizado.setEmail(usuario.getEmail());
            usuarioActualizado.setPassword(passwordEncoder.encode(usuario.getPassword()));
            return usuarioRepository.save(usuarioActualizado);
        } else {
            // Si el usuario no existe, puedes manejarlo según tus necesidades
            // Aquí lanzamos una excepción como ejemplo
            throw new EntityNotFoundException("Usuario no encontrado con ID: " + id);
        }
    }

    public void eliminarUsuario(Long id) {
        // Verifica si el usuario con el ID proporcionado existe
        Optional<Usuario> usuarioExistente = usuarioRepository.findById(id);
        if (usuarioExistente.isPresent()) {
            usuarioRepository.deleteById(id);
        } else {
            // Si el usuario no existe, puedes manejarlo según tus necesidades
            // Aquí lanzamos una excepción como ejemplo
            throw new EntityNotFoundException("Usuario no encontrado con ID: " + id);
        }
    }
}
