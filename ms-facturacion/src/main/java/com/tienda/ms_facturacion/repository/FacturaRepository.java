package com.tienda.ms_facturacion.repository;
import com.tienda.ms_facturacion.model.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository public interface FacturaRepository extends JpaRepository<Factura, Long> {}