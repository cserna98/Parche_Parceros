package com.ParcheParceros.Back.repository;

import com.ParcheParceros.Back.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    // Agregue aquí los métodos personalizados que necesite
}
