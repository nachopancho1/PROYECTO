package com.tienda.ms_ventas.repository;

import com.tienda.ms_ventas.model.DetalleVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

@Repository
public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Long> {
    // Esto servirá para buscar todos los productos de una boleta específica

    @Query("SELECT COUNT(d) FROM DetalleVenta d WHERE d.precioUnitario > :monto")
    long contarVentasQueSuperanMonto(@Param("monto") Double monto);
}