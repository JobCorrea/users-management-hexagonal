package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.dto.PublicarArticuloCommand;
import com.jcaa.usersmanagement.domain.exception.PublicacionNoPermitidaException;
import com.jcaa.usersmanagement.domain.model.Articulo;
import com.jcaa.usersmanagement.domain.model.ArticuloId;
import com.jcaa.usersmanagement.domain.port.out.ArticuloRepositoryPort;
import com.jcaa.usersmanagement.domain.port.out.NotificacionEventosPort;
import com.jcaa.usersmanagement.domain.service.ModeracionContenidoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Prueba Unitaria del Caso de Uso usando Mocks de los Puertos OUT:
 * Sustituye la base de datos y el bus de eventos por simuladores en memoria.
 */
class PublicarArticuloServiceTest {

    private Articulo articuloGuardadoEnMock;
    private List<Object> eventosPublicadosEnMock;
    private PublicarArticuloService casoDeUso;

    @BeforeEach
    void setUp() {
        // Reiniciamos el estado antes de cada prueba para garantizar total independencia
        articuloGuardadoEnMock = null;
        eventosPublicadosEnMock = new ArrayList<>();

        // Mock 1: Simula el Puerto de Salida de Persistencia (ArticuloRepositoryPort)
        ArticuloRepositoryPort repositoryMock = new ArticuloRepositoryPort() {
            @Override
            public void guardar(Articulo articulo) {
                articuloGuardadoEnMock = articulo; // Captura el agregado que intentó guardarse
            }

            @Override
            public Optional<Articulo> buscarPorId(ArticuloId id) {
                return Optional.ofNullable(articuloGuardadoEnMock);
            }
        };

        // Mock 2: Simula el Puerto de Salida de Eventos (NotificacionEventosPort)
        NotificacionEventosPort eventosMock = eventoDominio -> eventosPublicadosEnMock.add(eventoDominio);

        // Ensamblamos el Caso de Uso inyectando los Mocks por constructor
        casoDeUso = new PublicarArticuloService(
            new ModeracionContenidoService(),
            repositoryMock,
            eventosMock
        );
    }

    @Test
    @DisplayName("Caso Feliz: Debe validar, guardar el artículo en el repositorio Mock y publicar el evento")
    void debePublicarArticuloYNotificarEvento() {
        // Arrange: Preparamos el comando de entrada con datos válidos
        PublicarArticuloCommand command = new PublicarArticuloCommand(
            100L, 5L, "Patrones en Arquitectura Hexagonal", "Explicación detallada del flujo."
        );

        // Act: Ejecutamos el Puerto de Entrada
        casoDeUso.ejecutar(command);

        // Assert: Verificamos que ambos puertos OUT fueron invocados correctamente
        assertNotNull(articuloGuardadoEnMock, "El artículo debió guardarse en el repositorio Mock");
        assertEquals(100L, articuloGuardadoEnMock.getId().getValue());
        assertEquals(1, eventosPublicadosEnMock.size(), "Debió publicarse 1 evento de dominio");
    }

    @Test
    @DisplayName("Caso de Error: Si el contenido tiene spam, no debe guardar nada ni publicar eventos")
    void noDebeGuardarNiPublicarSiFallaModeracion() {
        // Arrange: Comando con contenido infractor
        PublicarArticuloCommand commandSpam = new PublicarArticuloCommand(
            101L, 5L, "Oferta de spam", "Contenido prohibido."
        );

        // Act & Assert: Debe lanzarse la excepción y los Mocks deben permanecer intactos
        assertThrows(PublicacionNoPermitidaException.class, () -> casoDeUso.ejecutar(commandSpam));
        assertNull(articuloGuardadoEnMock, "No debió invocarse el guardado en base de datos");
        assertTrue(eventosPublicadosEnMock.isEmpty(), "No debió publicarse ningún evento");
    }
}