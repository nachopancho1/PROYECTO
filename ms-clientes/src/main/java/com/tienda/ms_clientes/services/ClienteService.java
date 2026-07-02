package com.tienda.ms_clientes.services;

import com.tienda.ms_clientes.dto.ClienteDTO; 
import com.tienda.ms_clientes.dto.DireccionDTO;
import com.tienda.ms_clientes.dto.FidelidadDTO;
import com.tienda.ms_clientes.model.*;
import com.tienda.ms_clientes.repository.*;
import lombok.extern.slf4j.Slf4j; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

@Service
@Slf4j
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepo;

    @Autowired
    private DireccionRepository direccionRepo;

    @Autowired
    private FidelidadRepository fidelidadRepo;

    // MÉTODO 1: Listar Clientes
    public List<Cliente> listarClientes() {
        log.info("Roberto Marin: Iniciando consulta de lista completa de clientes en XAMPP");
        return clienteRepo.findAll();
    }

    // MÉTODO 2: Registrar Cliente (Crea Cliente + Fidelidad Automática)
    @Transactional
    public Cliente registrarCliente(ClienteDTO dto) {
        log.info("Roberto Marin: Procesando registro de cliente: {}", dto.getNombre());
        
        // 1. Mapeo: DTO -> Cliente
        Cliente c = new Cliente();
        c.setNombre(dto.getNombre());
        c.setEmail(dto.getEmail());
        c.setTelefono(dto.getTelefono());
        
        c.setPassword(dto.getPassword()); 
        
        Cliente nuevoCliente = clienteRepo.save(c);
        
        // 2. Regla de Negocio (Benyamin Arcapio): Crear fidelidad automática
        log.info("Benyamin Arcapio: Generando perfil de fidelidad inicial para el cliente ID: {}", nuevoCliente.getId());
        
        Fidelidad f = new Fidelidad();
        f.setPuntos(0);
        f.setNivel("Bronce");
        f.setFechaUltimaActualizacion(LocalDate.now().toString()); // Fecha actual
        f.setCliente(nuevoCliente);
        
        fidelidadRepo.save(f);
        
        return nuevoCliente;
    }

    // MÉTODO 3: Agregar Dirección (Usa DireccionDTO)
    @Transactional
    public Direccion agregarDireccion(DireccionDTO dto) {
        log.info("Benyamin Arcapio: Buscando cliente ID: {} para asignar direccion", dto.getClienteId());
        
        Cliente c = clienteRepo.findById(dto.getClienteId())
                .orElseThrow(() -> new RuntimeException("Error: Cliente no encontrado"));
        
        // Mapeo: DTO -> Direccion
        Direccion d = new Direccion();
        d.setCalle(dto.getCalle());
        d.setNumero(dto.getNumero());
        d.setCiudad(dto.getCiudad());
        d.setTipo(dto.getTipo());
        d.setCliente(c);
        
        return direccionRepo.save(d);
    }

    // MÉTODO 4: Consultar Fidelidad
    public Fidelidad consultarPuntos(Long clienteId) {
        log.info("Roberto Marin: Consultando estado de puntos para cliente ID: {}", clienteId);
        
        // Buscamos la fidelidad asociada a ese ID de cliente
        return fidelidadRepo.findAll().stream()
                .filter(f -> f.getCliente().getId().equals(clienteId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Perfil de fidelidad no encontrado para el cliente"));
    }

    // MÉTODO 5: Actualizar o Registrar Fidelidad Manualmente (Usa FidelidadDTO)
    @Transactional
    public Fidelidad actualizarFidelidad(FidelidadDTO dto) {
        log.info("Benyamin Arcapio: Actualizando puntos para el cliente ID: {}", dto.getClienteId());

        // 1. Buscamos al cliente
        Cliente c = clienteRepo.findById(dto.getClienteId())
                .orElseThrow(() -> new RuntimeException("Error: Cliente no encontrado"));

        // 2. Buscamos si ya tiene un perfil de fidelidad
        Fidelidad f = fidelidadRepo.findAll().stream()
                .filter(fid -> fid.getCliente().getId().equals(dto.getClienteId()))
                .findFirst()
                .orElse(new Fidelidad()); // Si no tiene, creamos uno nuevo

        // 3. Mapeamos los datos del DTO al Model
        f.setPuntos(dto.getPuntos());
        f.setNivel(dto.getNivel());
        f.setFechaUltimaActualizacion(dto.getFechaUltimaActualizacion());
        f.setCliente(c);

        return fidelidadRepo.save(f);
    }

    // MÉTODO 6: Buscar por Email (Benyamin Arcapio: Conexión para Login y Pruebas Unitarias)
        public Optional<Cliente> buscarPorEmail(String email) {
        log.info("Buscando cliente por email: {}", email);
        return clienteRepo.findByEmail(email);
    }

    
}