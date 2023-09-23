package com.example.Parche.repository;

import com.example.Parche.entity.Asistente;
import com.example.Parche.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    Optional<Item> findByNombre(String nombre);
    List<Item> findByParcheId(Long parcheId);

    Set<Item> findByAsistente(Asistente asistente);
}