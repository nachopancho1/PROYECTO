package com.tienda.ms_pedidos.repository;
import com.tienda.ms_pedidos.model.envio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository public interface EnvioRepository extends JpaRepository<envio, Long> {}