package com.tienda.ms_reclamos;

import com.tienda.ms_reclamos.dto.ReclamoDTO;
import com.tienda.ms_reclamos.model.Reclamo;
import com.tienda.ms_reclamos.repository.ReclamoRepository;
import com.tienda.ms_reclamos.services.ReclamoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MsReclamosApplicationTests {

    @Mock
    private ReclamoRepository reclamoRepo;

    @InjectMocks
    private ReclamoService reclamoService;

    @Test
    void testRegistrarReclamo_Exito() {
        // 1. Arrange (Preparar los datos de prueba)
        ReclamoDTO dto = new ReclamoDTO();
        dto.setAsunto("Producto dañado");
        dto.setDetalle("La caja del pedido llegó rota y aplastada");
        dto.setCategoria("Logística y Envíos");

        // Simulamos la respuesta de la base de datos
        Reclamo mockReclamoGuardado = new Reclamo();
        mockReclamoGuardado.setId(1L);
        mockReclamoGuardado.setAsunto("Producto dañado");
        mockReclamoGuardado.setDetalle("La caja del pedido llegó rota y aplastada");
        mockReclamoGuardado.setCategoria("Logística y Envíos");
        mockReclamoGuardado.setFechaRegistro(LocalDate.now()); // Simulamos la asignación automática

        // Entrenamos el mock
        when(reclamoRepo.save(any(Reclamo.class))).thenReturn(mockReclamoGuardado);

        // 2. Act (Ejecutar el método real)
        Reclamo resultado = reclamoService.registrarReclamo(dto);

        // 3. Assert (Verificar el comportamiento)
        assertNotNull(resultado, "El reclamo guardado no debe ser nulo");
        assertEquals("Producto dañado", resultado.getAsunto(), "El asunto debe mapearse correctamente");
        assertEquals("Logística y Envíos", resultado.getCategoria(), "La categoría debe coincidir");
        assertNotNull(resultado.getFechaRegistro(), "El sistema debe haberle asignado una fecha automáticamente");
        assertEquals(LocalDate.now(), resultado.getFechaRegistro(), "La fecha de registro debe ser la de hoy");

        // Trazabilidad
        verify(reclamoRepo, times(1)).save(any(Reclamo.class));
    }
}