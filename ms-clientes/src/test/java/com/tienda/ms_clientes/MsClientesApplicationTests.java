package com.tienda.ms_clientes;

import com.tienda.ms_clientes.model.Cliente;
import com.tienda.ms_clientes.repository.ClienteRepository;
import com.tienda.ms_clientes.services.ClienteService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional; 

// IMPORTS ESTÁTICOS PARA LOS TESTS 
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    @Test
    void testBuscarClientePorEmail_Exito() {
        // 1. Arrange
        String correoPrueba = "benyamin@correo.cl";
        Cliente mockCliente = new Cliente();
        mockCliente.setId(1L);
        mockCliente.setEmail(correoPrueba);
        mockCliente.setNombre("Benyamin");

        when(clienteRepository.findByEmail(correoPrueba)).thenReturn(Optional.of(mockCliente));

        // 2. Act
        Optional<Cliente> resultado = clienteService.buscarPorEmail(correoPrueba);

        // 3. Assert
        assertTrue(resultado.isPresent(), "El cliente debería existir");
        assertEquals("Benyamin", resultado.get().getNombre());
        
        verify(clienteRepository, times(1)).findByEmail(correoPrueba);
    }
}