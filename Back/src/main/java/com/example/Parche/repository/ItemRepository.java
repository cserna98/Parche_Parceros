package com.example.Parche.repository;

import com.example.Parche.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    // Puedes agregar consultas personalizadas aquí si es necesario
}