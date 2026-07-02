package com.tienda.ms_facturacion;

import com.tienda.ms_facturacion.dto.ReciboDTO;
import com.tienda.ms_facturacion.model.Factura;
import com.tienda.ms_facturacion.model.Recibo;
import com.tienda.ms_facturacion.repository.FacturaRepository;
import com.tienda.ms_facturacion.repository.ReciboRepository;
import com.tienda.ms_facturacion.service.FacturacionService; 
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MsFacturacionApplicationTests {

    @Mock
    private FacturaRepository facturaRepo;

    @Mock
    private ReciboRepository reciboRepo;

    @InjectMocks
    private FacturacionService facturacionService;

    @Test
    void testProcesarPagoRecibo_Exito() {
        // 1. Arrange (Preparar los datos simulados)
        // Simulamos el DTO del pago electrónico
        ReciboDTO dto = new ReciboDTO();
        dto.setFacturaId(15L);
        dto.setMetodoPago("Transferencia Bancaria");

        // Simulamos el documento tributario que ya existe en la BD
        Factura mockFactura = new Factura();
        mockFactura.setId(15L);
        mockFactura.setNumeroFactura("FAC-2026");
        mockFactura.setTotal(85000.0);

        // Simulamos el comprobante final que se guardará
        Recibo mockReciboGuardado = new Recibo();
        mockReciboGuardado.setId(1L);
        mockReciboGuardado.setMetodoPago("Transferencia Bancaria");
        mockReciboGuardado.setFactura(mockFactura);

        // Entrenamos los mocks
        when(facturaRepo.findById(15L)).thenReturn(Optional.of(mockFactura));
        when(reciboRepo.save(any(Recibo.class))).thenReturn(mockReciboGuardado);

        // 2. Act (Ejecutar el método real)
        Recibo resultado = facturacionService.procesarPagoRecibo(dto);

        // 3. Assert (Verificar que las reglas de facturación funcionaron)
        assertNotNull(resultado, "El recibo guardado no debe ser nulo");
        assertEquals("Transferencia Bancaria", resultado.getMetodoPago(), "El método de pago debe coincidir");
        assertNotNull(resultado.getFactura(), "El recibo debe estar amarrado a un documento tributario");
        assertEquals("FAC-2026", resultado.getFactura().getNumeroFactura(), "El folio de la factura debe coincidir");

        // Verificamos la trazabilidad del proceso
        verify(facturaRepo, times(1)).findById(15L);
        verify(reciboRepo, times(1)).save(any(Recibo.class));
    }
}