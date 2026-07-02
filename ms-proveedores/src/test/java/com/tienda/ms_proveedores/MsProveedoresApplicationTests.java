package com.tienda.ms_proveedores;

import com.tienda.ms_proveedores.dto.ProveedorDTO;
import com.tienda.ms_proveedores.model.Proveedor;
import com.tienda.ms_proveedores.model.Suministro;
import com.tienda.ms_proveedores.repository.ProveedorRepository;
import com.tienda.ms_proveedores.repository.SuministroRepository;
import com.tienda.ms_proveedores.services.ProveedorService;
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
class ProveedorServiceTest {

    @Mock
    private ProveedorRepository proveedorRepo;

    @Mock
    private SuministroRepository suministroRepo;

    @InjectMocks
    private ProveedorService proveedorService;

    @Test
    void testRegistrarProveedor_Exito() {
        // 1. Arrange (Preparar el escenario)
        // Simulamos el DTO que llega desde el Front-End
        ProveedorDTO dto = new ProveedorDTO();
        dto.setSuministroId(5L);
        dto.setRut("77.777.777-7");
        dto.setRazonSocial("Distribuidora Tecnológica SpA");

        // Simulamos el Suministro que la BD debe encontrar
        Suministro mockSuministro = new Suministro();
        mockSuministro.setId(5L);
        mockSuministro.setNombre("Hardware de Computadoras");

        // Simulamos el Proveedor final que devuelve la BD al guardar
        Proveedor mockProveedorGuardado = new Proveedor();
        mockProveedorGuardado.setId(1L);
        mockProveedorGuardado.setRut("77.777.777-7");
        mockProveedorGuardado.setRazonSocial("Distribuidora Tecnológica SpA");
        mockProveedorGuardado.setSuministro(mockSuministro);

        // Le enseñamos a Mockito cómo reaccionar
        when(suministroRepo.findById(5L)).thenReturn(Optional.of(mockSuministro));
        when(proveedorRepo.save(any(Proveedor.class))).thenReturn(mockProveedorGuardado);

        // 2. Act (Ejecutar la acción real de tu servicio)
        Proveedor resultado = proveedorService.registrarProveedor(dto);

        // 3. Assert (Verificar que todo funcionó según la regla de negocio)
        assertNotNull(resultado, "El proveedor no debe ser nulo");
        assertEquals("Distribuidora Tecnológica SpA", resultado.getRazonSocial(), "La razón social debe coincidir");
        assertNotNull(resultado.getSuministro(), "El proveedor debe tener un suministro asociado");
        assertEquals("Hardware de Computadoras", resultado.getSuministro().getNombre());

        // Verificamos que los repositorios se llamaron exactamente una vez
        verify(suministroRepo, times(1)).findById(5L);
        verify(proveedorRepo, times(1)).save(any(Proveedor.class));
    }
}