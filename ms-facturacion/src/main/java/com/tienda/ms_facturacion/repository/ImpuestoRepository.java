package com.tienda.ms_facturacion.repository;
import com.tienda.ms_facturacion.model.Impuesto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository public interface ImpuestoRepository extends JpaRepository<Impuesto, Long> {}