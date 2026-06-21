package com.tienda.ms_productos.repository;

import com.tienda.ms_productos.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    // Buscar productos que pertenezcan a una categoría específica
    List<Producto> findByCategoriaId(Long categoriaId);

}