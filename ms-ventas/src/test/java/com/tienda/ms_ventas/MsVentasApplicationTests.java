package com.tienda.ms_ventas;

import com.tienda.ms_ventas.dto.MetodoPagoDTO;
import com.tienda.ms_ventas.model.MetodoPago;
import com.tienda.ms_ventas.repository.MetodoPagoRepository;
import com.tienda.ms_ventas.services.VentaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VentaServiceTest {

    @Mock
    private MetodoPagoRepository pagoRepo; // Simulamos la base de datos de pagos

    @InjectMocks
    private VentaService ventaService; // Inyectamos el mock en tu servicio real

    @Test
    void testGuardarMetodoPago_Exito() {
        // 1. Arrange (Preparar)
        // Simulamos el DTO que enviaría el usuario desde Postman
        MetodoPagoDTO dto = new MetodoPagoDTO();
        dto.setTipo("Tarjeta de Credito");
        dto.setComprobante("Voucher123");

        // Simulamos la respuesta que daría la base de datos al guardar
        MetodoPago mockPagoGuardado = new MetodoPago();
        mockPagoGuardado.setId(1L);
        mockPagoGuardado.setTipo("Tarjeta de Credito");
        mockPagoGuardado.setComprobante("Voucher123");

        // Le decimos al Mock: "Cuando te pidan guardar CUALQUIER MetodoPago, devuelve nuestro mock"
        when(pagoRepo.save(any(MetodoPago.class))).thenReturn(mockPagoGuardado);

        // 2. Act (Actuar)
        // ¡AQUÍ ESTÁ LA MAGIA! Llamamos a tu método real que sí existe
        MetodoPago resultado = ventaService.guardarMetodoPago(dto);

        // 3. Assert (Verificar)
        assertNotNull(resultado, "El método de pago guardado no debe ser nulo");
        assertEquals("Tarjeta de Credito", resultado.getTipo(), "El tipo de pago debe coincidir");
        
        // Verificamos que el repositorio de pagos se utilizó exactamente una vez
        verify(pagoRepo, times(1)).save(any(MetodoPago.class));
    }
}