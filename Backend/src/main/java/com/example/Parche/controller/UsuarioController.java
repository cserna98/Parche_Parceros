package com.example.Parche.controller;

import com.example.Parche.DTO.UsuarioDTO;
import com.example.Parche.entity.usuario.Role;
import com.example.Parche.entity.usuario.RoleEnum;
import com.example.Parche.entity.usuario.Usuario;
import com.example.Parche.service.UsuarioService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/usuarios")
public class UsuarioController {

    @Autowired
    private ModelMapper modelMapper;

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioService.obtenerTodosLosUsuarios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> obtenerUsuarioPorId(@PathVariable Long id) {
        Optional<Usuario> usuarioOptional = usuarioService.getUserById(id);

        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            UsuarioDTO usuarioDTO = modelMapper.map(usuario, UsuarioDTO.class);

            return ResponseEntity.ok(usuarioDTO);
        } else {
            // Manejar el caso en el que el usuario no se encuentre
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody Usuario user){

        Set<Role> roles = user.getRoles().stream()
                .map(usuarioRole -> {
                    Role role = new Role();
                    role.setName(usuarioRole.getName());
                    return role;
                })
                .collect(Collectors.toSet());

        Usuario userEntity = Usuario.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .roles(roles)
                .build();

        usuarioService.crearUsuario(userEntity);

        return ResponseEntity.ok(userEntity);
    }

    @PutMapping("/{id}")
    public Usuario actualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        return usuarioService.actualizarUsuario(id, usuario);
    }

    @DeleteMapping("/{id}")
    public void eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
    }
}
