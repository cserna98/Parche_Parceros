package com.example.Parche.repository;

import com.example.Parche.entity.Parche;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParcheRepository extends JpaRepository<Parche, Long> {
    Optional<Parche> findByNombre(String nombre);
}