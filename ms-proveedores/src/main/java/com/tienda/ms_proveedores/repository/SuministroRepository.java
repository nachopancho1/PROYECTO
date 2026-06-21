package com.tienda.ms_proveedores.repository;

import com.tienda.ms_proveedores.model.Suministro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuministroRepository extends JpaRepository<Suministro, Long> {
}