package com.tienda.ms_inventario.repository;

import com.tienda.ms_inventario.model.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {

    // Contador que cuenta las "ENTRADAS" registradas en tipo de movimiento
    @Query("SELECT COUNT(m) FROM Movimiento m WHERE m.tipoMovimiento = 'ENTRADA'")
    long contarTotalEntradas();

    // Lo mismo pero con salida jsj
    @Query("SELECT COUNT(m) FROM Movimiento m WHERE m.tipoMovimiento = 'SALIDA'")
    long contarTotalSalidas();
}