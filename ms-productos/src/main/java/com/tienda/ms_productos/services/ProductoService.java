package com.tienda.ms_productos.services;

import com.tienda.ms_productos.dto.*;
import com.tienda.ms_productos.model.*;
import com.tienda.ms_productos.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Slf4j
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepo;
    
    @Autowired
    private CategoriaRepository categoriaRepo;
    
    @Autowired
    private OfertaRepository ofertaRepo;

    // --- LÓGICA DE PRODUCTOS ---
    public List<Producto> listarProductos() {
        log.info("Roberto Marin: Consultando todos los productos registrados en XAMPP");
        return productoRepo.findAll();
    }

    @Transactional
    public Producto guardarProducto(ProductoDTO dto) {
        log.info("Benyamin Arcapio: Mapeando producto {} con categoria ID {}", dto.getNombre(), dto.getCategoriaId());
        
        Categoria cat = categoriaRepo.findById(dto.getCategoriaId())
                .orElseThrow(() -> new RuntimeException("Categoria no encontrada"));

        Producto p = new Producto();
        p.setNombre(dto.getNombre());
        p.setPrecioBase(dto.getPrecioBase());
        p.setCategoria(cat);

        return productoRepo.save(p);
    }

    // --- LÓGICA DE CATEGORÍAS ---
    @Transactional
    public Categoria guardarCategoria(CategoriaDTO dto) {
        log.info("Benyamin Arcapio: Creando nueva categoría: {}", dto.getNombre());
        Categoria c = new Categoria();
        c.setNombre(dto.getNombre());
        return categoriaRepo.save(c);
    }

    // --- LÓGICA DE OFERTAS ---
    @Transactional
    public Oferta crearOferta(OfertaDTO dto) {
        log.info("Roberto Marin: Buscando producto ID {} para aplicar oferta", dto.getProductoId());
        
        Producto p = productoRepo.findById(dto.getProductoId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        log.info("Benyamin Arcapio: Creando oferta: {}", dto.getDescripcion());
        
        Oferta o = new Oferta();
        o.setPorcentajeDescuento(dto.getPorcentajeDescuento());
        o.setDescripcion(dto.getDescripcion()); // Cambiado de fechaFin a descripcion para calzar con tu Model
        o.setProducto(p);

        return ofertaRepo.save(o);
    }

    // --- CÁLCULO DE DESCUENTO ---
    @Transactional
    public Producto aplicarOfertaExistenteAProducto(Long ofertaId) {
        Oferta oferta = ofertaRepo.findById(ofertaId)
                .orElseThrow(() -> new RuntimeException("Oferta no encontrada con ID: " + ofertaId));

        Producto producto = oferta.getProducto();
        if (producto == null) {
            throw new RuntimeException("Esta oferta no tiene un producto asignado");
        }

        log.info("Benyamin Arcapio: Aplicando descuento de {}% al producto: {}", 
                 oferta.getPorcentajeDescuento(), producto.getNombre());

        double precioOriginal = producto.getPrecioBase();
        double factorDescuento = oferta.getPorcentajeDescuento() / 100.0;
        double nuevoPrecio = precioOriginal * (1 - factorDescuento);
        
        producto.setPrecioBase(nuevoPrecio);

        return productoRepo.save(producto);
    }
    // --- LÓGICA DE OFERTAS ---
    
    // MÉTODO: Listar 
    public List<Oferta> listarOfertas() {
        log.info("Roberto Marin: Consultando lista completa de ofertas en la base de datos");
        return ofertaRepo.findAll();
    }
    public List<Categoria> listarCategorias() {
    log.info("Roberto Marin: Consultando todas las categorías en XAMPP");
    return categoriaRepo.findAll();
    }
}