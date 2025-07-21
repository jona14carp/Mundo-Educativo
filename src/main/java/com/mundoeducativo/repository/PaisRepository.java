package com.mundoeducativo.repository;

import com.mundoeducativo.model.Pais;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaisRepository extends JpaRepository<Pais, Long> {

    List<Pais> findByContinente(String continente);

}



