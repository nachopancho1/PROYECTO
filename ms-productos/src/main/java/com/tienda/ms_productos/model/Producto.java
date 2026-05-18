package com.tienda.ms_productos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "productos")
@Data
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @Min(value = 1, message = "El precio debe ser mayor a 0")
    private double precioBase;

    @PositiveOrZero(message = "El stock no puede ser negativo")
    private int stock;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    @JsonManagedReference // Muestra la categoría al consultar el producto
    private Categoria categoria;

    @OneToOne(mappedBy = "producto", cascade = CascadeType.ALL)
    @JsonManagedReference // Muestra la oferta al consultar el producto
    private Oferta ofertaActiva;
}