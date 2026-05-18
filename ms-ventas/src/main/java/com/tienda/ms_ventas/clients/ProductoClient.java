package com.tienda.ms_ventas.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

// Aquí le decimos: "Llama a este vecino que está en el puerto 8081"
@FeignClient(name = "ms-productos", url = "localhost:8081/api/productos")
public interface ProductoClient {

    // Este es el botón que vamos a apretar para descontar stock
    @PutMapping("/{id}/stock")
    void actualizarStock(@PathVariable("id") Long id, @RequestParam("cantidad") int cantidad);
}