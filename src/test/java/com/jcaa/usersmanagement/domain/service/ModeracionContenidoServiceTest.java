package com.jcaa.usersmanagement.domain.service;

import com.jcaa.usersmanagement.domain.exception.PublicacionNoPermitidaException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.jcaa.usersmanagement.domain.service.ModeracionContenidoService;

/**
 * Prueba Unitaria Pura del Servicio de Dominio:
 * Evalúa las políticas de moderación de texto de forma aislada y determinista.
 */
class ModeracionContenidoServiceTest {

    private final ModeracionContenidoService moderacionService = new ModeracionContenidoService();

    @Test
    @DisplayName("Caso de Error: Debe bloquear textos que contengan palabras prohibidas como estafa o spam")
    void debeBloquearContenidoConPalabrasProhibidas() {
        // Arrange: Texto infractor
        String textoInvalido = "Haz clic aquí para ganar dinero con esta estafa asegurada";

        // Act & Assert: El servicio interrumpe el proceso lanzando la excepción de dominio
        assertThrows(PublicacionNoPermitidaException.class, () -> {
            moderacionService.validarContenido(textoInvalido);
        });
    }

    @Test
    @DisplayName("Caso Feliz: Debe aprobar publicaciones con lenguaje técnico adecuado")
    void debeAprobarContenidoValido() {
        // Arrange: Texto limpio
        String textoLimpio = "Implementación de puertos y adaptadores en Java.";

        // Act & Assert: No debe arrojar ninguna excepción
        assertDoesNotThrow(() -> {
            moderacionService.validarContenido(textoLimpio);
        });
    }
}