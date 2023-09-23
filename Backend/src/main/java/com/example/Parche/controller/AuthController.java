package com.example.Parche.controller;


import com.example.Parche.DTO.LoginDto;
import com.example.Parche.security.jwt.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final Set<String> invalidTokens = ConcurrentHashMap.newKeySet();

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto) {
        UsernamePasswordAuthenticationToken login = new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());
        System.out.println("es autentificado " + login);
        try {
            Authentication authentication = this.authenticationManager.authenticate(login);
            System.out.print("Autenticación exitosa: {}" + authentication);
            String jwt = this.jwtUtil.create(loginDto.getEmail());

            return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, jwt).build();
            // Resto del código...
        } catch (AuthenticationException e) {
            e.printStackTrace();
            System.out.print("Error de autenticación: {}" + e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            // Manejo de errores...
        }


    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request) {
        // Recuperar el token JWT del encabezado de autorización
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String jwt = authHeader.substring(7); // Eliminar "Bearer " para obtener solo el token

            // Implementa aquí la lógica para invalidar el token en el servidor
            // Puedes almacenar tokens inválidos en una lista o base de datos si es necesario
            // Por ejemplo, puedes tener una lista de tokens inválidos en memoria o en una base de datos

            // Agregar el token actual a la lista de tokens inválidos
            invalidTokens.add(jwt);

            // Aquí asumimos que la sesión se invalida automáticamente al cerrar sesión
            // Si necesitas una lógica más específica para invalidar tokens, agrégala aquí

            // Invalida la sesión del usuario
            request.getSession().invalidate();

            // En este punto, la sesión del usuario se ha cerrado y el token JWT se considera inválido

            return ResponseEntity.ok().build();
        } else {
            // Si el encabezado de autorización no es válido, puedes manejarlo aquí
            return ResponseEntity.badRequest().build();
        }
    }
}