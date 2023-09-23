package com.example.Parche.repository;


import com.example.Parche.entity.Asistente;
import com.example.Parche.entity.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AsistenteRepository extends JpaRepository<Asistente, Long> {
    @Query("SELECT a FROM Asistente a WHERE a.firstname = :nombre")
    List<Asistente> findByNombre(@Param("nombre") String nombre);
    List<Asistente> findByParcheId(Long parcheId);
}