package com.jcaa.usersmanagement.domain.model;

import com.jcaa.usersmanagement.domain.exception.ArticuloRechazadoException;
import com.jcaa.usersmanagement.domain.factory.ArticuloFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Prueba Unitaria de la Raíz de Agregado Articulo:
 * Demuestra que el agregado protege sus invariantes de estado sin depender de infraestructura.
 */
class ArticuloTest {

    @Test
    @DisplayName("Invariante Crítica: No debe permitir agregar comentarios si el artículo fue rechazado")
    void noDebePermitirComentarArticuloRechazado() {
        // Arrange: Creamos un artículo activo mediante la fábrica y lo rechazamos
        Articulo articulo = ArticuloFactory.crearArticulo(1L, 10L, "Arquitectura Hexagonal", "Contenido técnico válido.");
        articulo.rechazar("Incumple normas de formato");

        // Act & Assert: Intentar comentar dispara la excepción de negocio ArticuloRechazadoException
        assertThrows(ArticuloRechazadoException.class, () -> {
            articulo.agregarComentario("Intento de comentario en publicación bloqueada");
        });
    }

    @Test
    @DisplayName("Caso Feliz: Debe permitir agregar comentarios cuando el artículo está activo")
    void debePermitirComentarArticuloActivo() {
        // Arrange: Instanciamos un artículo en estado activo por defecto
        Articulo articulo = ArticuloFactory.crearArticulo(2L, 10L, "Guía de DDD", "Explicación de agregados.");

        // Act: Agregamos un comentario válido
        articulo.agregarComentario("Excelente aporte para el proyecto.");

        // Assert: Verificamos que la colección encapsulada contenga exactamente 1 comentario
        assertEquals(1, articulo.getComentarios().size());
        assertEquals("Excelente aporte para el proyecto.", articulo.getComentarios().get(0));
    }
}