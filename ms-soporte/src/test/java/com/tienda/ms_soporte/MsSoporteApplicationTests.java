package com.tienda.ms_soporte;

import com.tienda.ms_soporte.dto.TicketSoporteDTO;
import com.tienda.ms_soporte.model.TicketSoporte;
import com.tienda.ms_soporte.repository.TicketSoporteRepository;
import com.tienda.ms_soporte.services.SoporteService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SoporteServiceTest {

    @Mock
    private TicketSoporteRepository ticketRepo;

    @InjectMocks
    private SoporteService soporteService;

    @Test
    void testCrearTicket_Exito() {
        // 1. Arrange (Preparar)
        // Simulamos los datos que enviaría el cliente desde el Front-End
        TicketSoporteDTO dto = new TicketSoporteDTO();
        dto.setClienteId(100L);
        dto.setAsunto("Problema con el pedido");
        dto.setDescripcion("Mi producto llegó roto");
        dto.setPrioridad("ALTA");

        // Simulamos lo que devolvería la base de datos al guardar
        TicketSoporte mockTicketGuardado = new TicketSoporte();
        mockTicketGuardado.setId(1L);
        mockTicketGuardado.setClienteId(100L);
        mockTicketGuardado.setAsunto("Problema con el pedido");
        mockTicketGuardado.setDescripcion("Mi producto llegó roto");
        mockTicketGuardado.setPrioridad("ALTA");
        mockTicketGuardado.setEstado("ABIERTO"); // La regla de negocio de tu método

        // Entrenamos al Mock
        when(ticketRepo.save(any(TicketSoporte.class))).thenReturn(mockTicketGuardado);

        // 2. Act (Actuar)
        // Llamamos a tu método real
        TicketSoporte resultado = soporteService.crearTicket(dto);

        // 3. Assert (Verificar)
        assertNotNull(resultado, "El ticket guardado no debe ser nulo");
        assertEquals("ABIERTO", resultado.getEstado(), "El sistema debe asignar el estado ABIERTO automáticamente");
        assertEquals(100L, resultado.getClienteId(), "El ID del cliente debe coincidir");
        
        // Verificamos que se haya intentado guardar en la base de datos 1 sola vez
        verify(ticketRepo, times(1)).save(any(TicketSoporte.class));
    }
}