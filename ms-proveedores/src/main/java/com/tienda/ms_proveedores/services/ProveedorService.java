package com.tienda.ms_proveedores.services;

import com.tienda.ms_proveedores.dto.ContactoDTO;
import com.tienda.ms_proveedores.dto.ProveedorDTO;
import com.tienda.ms_proveedores.dto.SuministroDTO;
import com.tienda.ms_proveedores.model.*;
import com.tienda.ms_proveedores.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class ProveedorService {
    private static final Logger log = LoggerFactory.getLogger(ProveedorService.class);

    @Autowired
    private ProveedorRepository proveedorRepo;

    @Autowired
    private ContactoRepository contactoRepo;

    // --- LÓGICA DE PROVEEDORES ---
    public List<Proveedor> listarProveedores() {
        log.info("Consultando lista de proveedores registrados");
        return proveedorRepo.findAll();
    }

    @Transactional
    public Proveedor registrarProveedor(Proveedor p) {
        log.info("Registrando proveedor: {}", p.getRazonSocial());
        return proveedorRepo.save(p);
    }

    public Proveedor buscarPorId(Long id) {
        return proveedorRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));
    }

    // --- LÓGICA DE CONTACTOS ---
    @Transactional
    public Contacto asignarContacto(Long proveedorId, Contacto c) {
        Proveedor p = buscarPorId(proveedorId);
        c.setProveedor(p);
        log.info("Asignando contacto técnico al proveedor: {}", p.getRazonSocial());
        return contactoRepo.save(c);
    }
    // Agrega esta inyección arriba con los otros @Autowired
    @Autowired
    private SuministroRepository suministroRepo;

    // --- LÓGICA DE SUMINISTROS ---
    public List<Suministro> listarSuministros() {
        log.info("Consultando catálogo de tipos de suministro");
        return suministroRepo.findAll();
    }

    @Transactional
    public Suministro guardarSuministro(Suministro s) {
        log.info("Creando nueva categoría de suministro: {}", s.getNombre());
        return suministroRepo.save(s);
    }
    @Transactional
    public Contacto guardarContacto(ContactoDTO dto) {
        // Roberto Marin: Buscando proveedor para asociar contacto
        Proveedor p = proveedorRepo.findById(dto.getProveedorId())
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));

        log.info("Benyamin Arcapio: Asignando ejecutivo {} al proveedor {}", 
                 dto.getNombreEjecutivo(), p.getRazonSocial());

        Contacto c = new Contacto();
        c.setNombreEjecutivo(dto.getNombreEjecutivo());
        c.setEmail(dto.getEmail());
        c.setTelefono(dto.getTelefono());
        c.setProveedor(p); // Establece la relación OneToOne

        return contactoRepo.save(c);
    }
    // 1. Guardar Suministro (Debe recibir el DTO)
    @Transactional
    public Suministro guardarSuministro(SuministroDTO dto) {
        log.info("Benyamin Arcapio: Creando categoría de suministro: {}", dto.getNombre());
        Suministro s = new Suministro();
        s.setNombre(dto.getNombre());
        return suministroRepo.save(s);
    }

    // 2. Registrar Proveedor (Debe recibir el DTO para buscar el ID del suministro)
    @Transactional
    public Proveedor registrarProveedor(ProveedorDTO dto) {
        log.info("Roberto Marin: Buscando suministro ID {} para el proveedor {}", 
                 dto.getSuministroId(), dto.getRazonSocial());
        
        // Buscamos el suministro en la BD
        Suministro s = suministroRepo.findById(dto.getSuministroId())
                .orElseThrow(() -> new RuntimeException("Error: El suministro no existe"));

        Proveedor p = new Proveedor();
        p.setRut(dto.getRut());
        p.setRazonSocial(dto.getRazonSocial());
        p.setSuministro(s); // Aquí se vinculan

        return proveedorRepo.save(p);
    }
}