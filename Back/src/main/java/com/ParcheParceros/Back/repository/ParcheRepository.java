package com.ParcheParceros.Back.repository;

import com.ParcheParceros.Back.entity.Parche;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParcheRepository extends JpaRepository<Parche, Long> {
}