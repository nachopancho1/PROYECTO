package com.tienda.ms_facturacion.repository;
import com.tienda.ms_facturacion.model.Recibo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository public interface ReciboRepository extends JpaRepository<Recibo, Long> {}