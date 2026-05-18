package com.tienda.ms_clientes.repository;

import com.tienda.ms_clientes.model.Fidelidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface FidelidadRepository extends JpaRepository<Fidelidad, Long> {
    // Este método es necesario para que el Service encuentre la fidelidad por el ID del cliente
    Optional<Fidelidad> findByClienteId(Long clienteId);
}