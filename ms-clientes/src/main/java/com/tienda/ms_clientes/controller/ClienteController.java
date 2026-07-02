package com.tienda.ms_clientes.controller;

import com.tienda.ms_clientes.dto.ClienteDTO; 
import com.tienda.ms_clientes.dto.LoginDTO; 
import com.tienda.ms_clientes.model.Cliente;
import com.tienda.ms_clientes.services.ClienteService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.security.Key;
import java.util.List;
import java.util.Optional;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @GetMapping
    public ResponseEntity<List<Cliente>> listar() {
        // Roberto Marin: Trazabilidad en el endpoint de lectura
        return ResponseEntity.ok(service.listarClientes());
    }

    @PostMapping
    public ResponseEntity<Cliente> crear(@Valid @RequestBody ClienteDTO dto) { 
        // Benyamin Arcapio: Recibimos el DTO y el Service se encarga del mapeo
        Cliente nuevo = service.registrarCliente(dto); 
        return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
    }

   @PostMapping("/login")
    public ResponseEntity<?> iniciarSesion(@Valid @RequestBody LoginDTO loginDTO) {
        Optional<Cliente> clienteOpt = service.buscarPorEmail(loginDTO.getEmail());

        if (clienteOpt.isPresent() && clienteOpt.get().getPassword().equals(loginDTO.getPassword())) {
            
            // 1. Clave secreta (debe tener al menos 32 caracteres)
            String secretKey = "mi_clave_secreta_super_larga_de_32_caracteres_minimo";
            Key key = Keys.hmacShaKeyFor(secretKey.getBytes());

            // 2. Generación del JWT profesional
            String token = Jwts.builder()
                .setSubject(clienteOpt.get().getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // 1 hora
                .signWith(key)
                .compact();
            
            // 3. Respuesta final
            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("mensaje", "Inicio de sesión correcto");
            respuesta.put("token", token);
            respuesta.put("cliente", clienteOpt.get().getNombre());

            return ResponseEntity.ok(respuesta);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error: Credenciales inválidas");
    }
}