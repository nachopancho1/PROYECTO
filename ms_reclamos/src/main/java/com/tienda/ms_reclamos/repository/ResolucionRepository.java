package com.tienda.ms_reclamos.repository;

import com.tienda.ms_reclamos.model.Resolucion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResolucionRepository extends JpaRepository<Resolucion, Long> {
}