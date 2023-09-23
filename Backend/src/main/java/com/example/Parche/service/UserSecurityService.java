package com.example.Parche.service;


import com.example.Parche.entity.usuario.Role;
import com.example.Parche.entity.usuario.Usuario;
import com.example.Parche.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserSecurityService implements UserDetailsService {


    private final UsuarioRepository userRepository;

    @Autowired
    public UserSecurityService(UsuarioRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario userEntity = this.userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User " + email + " not found."));

        System.out.println(userEntity);

        //String[] roles = userEntity.getRoles().stream().map(Role::getName).toArray(String[]::new);

        // Convertir los objetos RoleEnum a cadenas
        String[] roles = userEntity.getRoles().stream()
                .map(role -> role.getName().toString()) // Convertir RoleEnum a String
                .toArray(String[]::new);

        return User.builder()
                .username(userEntity.getEmail())
                .password(userEntity.getPassword())
                .authorities(this.grantedAuthorities(roles))

                .build();
    }

    private String[] getAuthorities(String role) {
        if ("ADMIN".equals(role) || "USER".equals(role)) {
            return new String[] {"random_order"};
        }

        return new String[] {};
    }

    private List<GrantedAuthority> grantedAuthorities(String[] roles) {
        List<GrantedAuthority> authorities = new ArrayList<>(roles.length);

        for (String role: roles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role));

            for (String authority: this.getAuthorities(role)) {
                authorities.add(new SimpleGrantedAuthority(authority));
            }
        }

        return authorities;
    }
}