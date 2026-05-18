package com.tienda.ms_clientes.repository;

import com.tienda.ms_clientes.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    // Puedes agregar búsquedas personalizadas aquí si el profe te lo pide
    Cliente findByEmail(String email);
}