package com.tienda.ms_pedidos;

import com.tienda.ms_pedidos.dto.EnvioDTO;
import com.tienda.ms_pedidos.model.Pedido;
import com.tienda.ms_pedidos.model.envio; 
import com.tienda.ms_pedidos.repository.EnvioRepository;
import com.tienda.ms_pedidos.repository.PedidoRepository;
import com.tienda.ms_pedidos.service.PedidoService; 
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
class MsPedidosApplicationTests {

    @Mock
    private PedidoRepository pedidoRepo;

    @Mock
    private EnvioRepository envioRepo;

    @InjectMocks
    private PedidoService pedidoService;

    @Test
    void testRegistrarEnvio_Exito() {
        // 1. Arrange (Preparar los datos de prueba)
        // Simulamos el DTO que enviaría el usuario con la dirección
        EnvioDTO dto = new EnvioDTO();
        dto.setPedidoId(10L);
        dto.setDireccionEnvio("Avenida Las Condes 1234");

        // Simulamos el Pedido que debe existir en XAMPP
        Pedido mockPedido = new Pedido();
        mockPedido.setId(10L);
        mockPedido.setCodigo("PED-9999");
        mockPedido.setClienteId(500L);

        // Simulamos el objeto 'envio' que se guarda en la base de datos
        envio mockEnvioGuardado = new envio();
        mockEnvioGuardado.setId(1L);
        mockEnvioGuardado.setDireccionEnvio("Avenida Las Condes 1234");
        mockEnvioGuardado.setPedido(mockPedido);

        // Le enseñamos a Mockito cómo reaccionar
        when(pedidoRepo.findById(10L)).thenReturn(Optional.of(mockPedido));
        when(envioRepo.save(any(envio.class))).thenReturn(mockEnvioGuardado);

        // 2. Act (Ejecutar el método real)
        envio resultado = pedidoService.registrarEnvio(dto);

        // 3. Assert (Verificar que las reglas de negocio funcionaron)
        assertNotNull(resultado, "El envío registrado no debe ser nulo");
        assertEquals("Avenida Las Condes 1234", resultado.getDireccionEnvio(), "La dirección debe coincidir");
        assertNotNull(resultado.getPedido(), "El envío debe estar amarrado a un pedido");
        assertEquals("PED-9999", resultado.getPedido().getCodigo(), "El código del pedido amarrado debe coincidir");

        // Verificamos que los repositorios fueron llamados exactamente una vez
        verify(pedidoRepo, times(1)).findById(10L);
        verify(envioRepo, times(1)).save(any(envio.class));
    }
}