package com.tienda.ms_clientes.repository;

import com.tienda.ms_clientes.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional; 

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    
    // Envolvemos al Cliente en un Optional para evitar los errores de nulos
    Optional<Cliente> findByEmail(String email);
}