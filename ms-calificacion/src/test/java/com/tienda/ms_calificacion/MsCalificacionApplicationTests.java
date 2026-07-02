package com.tienda.ms_calificacion;

import com.tienda.ms_calificacion.dto.CalificacionDTO;
import com.tienda.ms_calificacion.model.Calificacion;
import com.tienda.ms_calificacion.repository.CalificacionRepository;
import com.tienda.ms_calificacion.services.CalificacionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MsCalificacionApplicationTests {

    @Mock
    private CalificacionRepository califRepo;

    @InjectMocks
    private CalificacionService calificacionService;

    @Test
    void testRegistrarCalificacion_Exito() {
        // 1. Arrange (Preparar los datos simulados)
        // Simulamos lo que ingresa el usuario en el Front-End (DTO)
        CalificacionDTO dto = new CalificacionDTO();
        dto.setPuntaje(5); // Asumiendo que puntaje es numérico (ej: 5 estrellas)
        dto.setComentario("Excelente atención, los productos llegaron al tiro");

        // Simulamos lo que devuelve XAMPP/JPA al guardar en la base de datos (Model)
        Calificacion mockCalificacionGuardada = new Calificacion();
        mockCalificacionGuardada.setId(1L);
        mockCalificacionGuardada.setEstrellas(5);
        mockCalificacionGuardada.setComentario("Excelente atención, los productos llegaron al tiro");

        // Le enseñamos a Mockito cómo debe comportarse el repositorio
        when(califRepo.save(any(Calificacion.class))).thenReturn(mockCalificacionGuardada);

        // 2. Act (Ejecutar la lógica de negocio de Ignacio)
        Calificacion resultado = calificacionService.registrarCalificacion(dto);

        // 3. Assert (Verificar que los datos se mapearon y guardaron bien)
        assertNotNull(resultado, "La calificación guardada no debe ser nula");
        assertEquals(5, resultado.getEstrellas(), "Las estrellas deben coincidir con el puntaje del DTO");
        assertEquals("Excelente atención, los productos llegaron al tiro", resultado.getComentario(), "El comentario debe coincidir");

        // Verificamos que el método save() se llamó 1 sola vez para evitar guardados dobles
        verify(califRepo, times(1)).save(any(Calificacion.class));
    }
}