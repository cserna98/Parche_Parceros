package com.example.Parche.repository;

import com.example.Parche.entity.Parche;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParcheRepository extends JpaRepository<Parche, Long> {
    // Puedes agregar consultas personalizadas aquí si es necesario
}