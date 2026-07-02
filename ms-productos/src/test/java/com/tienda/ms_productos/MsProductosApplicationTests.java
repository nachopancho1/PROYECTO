package com.tienda.ms_productos;

import com.tienda.ms_productos.dto.ProductoDTO;
import com.tienda.ms_productos.model.Categoria;
import com.tienda.ms_productos.model.Producto;
import com.tienda.ms_productos.repository.CategoriaRepository;
import com.tienda.ms_productos.repository.ProductoRepository;
import com.tienda.ms_productos.services.ProductoService;
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
class MsProductosApplicationTests {

    @Mock
    private ProductoRepository productoRepo;

    @Mock
    private CategoriaRepository categoriaRepo;

    @InjectMocks
    private ProductoService productoService;

    @Test
    void testGuardarProducto_Exito() {
        // 1. Arrange (Preparar los datos de prueba simulados)
        ProductoDTO dto = new ProductoDTO();
        dto.setNombre("Notebook Lenovo Legion");
        dto.setPrecioBase(1200000.0);
        dto.setCategoriaId(10L);

        Categoria mockCategoria = new Categoria();
        mockCategoria.setId(10L);
        mockCategoria.setNombre("Computación");

        Producto mockProductoGuardado = new Producto();
        mockProductoGuardado.setId(1L);
        mockProductoGuardado.setNombre("Notebook Lenovo Legion");
        mockProductoGuardado.setPrecioBase(1200000.0);
        mockProductoGuardado.setCategoria(mockCategoria);

        // Entrenamos los mocks para simular el comportamiento de XAMPP / JPA
        when(categoriaRepo.findById(10L)).thenReturn(Optional.of(mockCategoria));
        when(productoRepo.save(any(Producto.class))).thenReturn(mockProductoGuardado);

        // 2. Act (Ejecutar la acción real del servicio)
        Producto resultado = productoService.guardarProducto(dto);

        // 3. Assert (Verificar que las reglas de negocio se cumplieron)
        assertNotNull(resultado, "El producto guardado no debería ser nulo");
        assertEquals("Notebook Lenovo Legion", resultado.getNombre(), "El nombre mapeado debe ser idéntico");
        assertEquals(1200000.0, resultado.getPrecioBase(), "El precio base debe mantenerse");
        assertEquals("Computación", resultado.getCategoria().getNombre(), "La categoría asociada debe coincidir");

        // Verificaciones de trazabilidad del repositorio
        verify(categoriaRepo, times(1)).findById(10L);
        verify(productoRepo, times(1)).save(any(Producto.class));
    }
}