package com.tienda.ms_inventario;

import com.tienda.ms_inventario.dto.MovimientoDTO;
import com.tienda.ms_inventario.model.Bodega;
import com.tienda.ms_inventario.model.Movimiento;
import com.tienda.ms_inventario.repository.BodegaRepository;
import com.tienda.ms_inventario.repository.MovimientoRepository;
import com.tienda.ms_inventario.services.InventarioService;
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
class MsInventarioApplicationTests {

    @Mock
    private MovimientoRepository movimientoRepo;

    @Mock
    private BodegaRepository bodegaRepo;

    @InjectMocks
    private InventarioService inventarioService;

    @Test
    void testRegistrarMovimiento_Exito() {
        // 1. Arrange (Preparar los datos simulados)
        MovimientoDTO dto = new MovimientoDTO();
        dto.setProductoId(99L);
        dto.setTipoMovimiento("ENTRADA");
        dto.setCantidad(50);
        dto.setBodegaId(5L);

        Bodega mockBodega = new Bodega();
        mockBodega.setId(5L);
        mockBodega.setNombre("Bodega Maipú Central");

        Movimiento mockMovimientoGuardado = new Movimiento();
        mockMovimientoGuardado.setId(1L);
        mockMovimientoGuardado.setProductoId(99L);
        mockMovimientoGuardado.setTipoMovimiento("ENTRADA");
        mockMovimientoGuardado.setCantidad(50);
        mockMovimientoGuardado.setBodega(mockBodega);

        // Entrenamos los mocks para que respondan como XAMPP
        when(bodegaRepo.findById(5L)).thenReturn(Optional.of(mockBodega));
        when(movimientoRepo.save(any(Movimiento.class))).thenReturn(mockMovimientoGuardado);

        // 2. Act (Ejecutar el método real)
        Movimiento resultado = inventarioService.registrarMovimiento(dto);

        // 3. Assert (Verificar que todo cuadra perfectamente)
        assertNotNull(resultado, "El movimiento registrado no debe ser nulo");
        assertEquals("ENTRADA", resultado.getTipoMovimiento(), "El tipo de movimiento debe coincidir");
        assertEquals(50, resultado.getCantidad(), "La cantidad debe ser exacta");
        assertNotNull(resultado.getBodega(), "El movimiento debe estar asociado a una bodega");
        assertEquals("Bodega Maipú Central", resultado.getBodega().getNombre(), "El nombre de la bodega debe coincidir");

        // Verificamos la trazabilidad de los repositorios
        verify(bodegaRepo, times(1)).findById(5L);
        verify(movimientoRepo, times(1)).save(any(Movimiento.class));
    }
}