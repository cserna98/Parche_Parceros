package com.example.Parche.repository;


import com.example.Parche.entity.Asistente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AsistenteRepository extends JpaRepository<Asistente, Long> {
    Optional<Asistente> findByNombre(String nombre);
}