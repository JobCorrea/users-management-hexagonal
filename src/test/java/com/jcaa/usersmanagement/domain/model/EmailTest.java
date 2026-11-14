package com.jcaa.usersmanagement.domain.model;

import com.jcaa.usersmanagement.domain.exception.InvalidEmailException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Prueba Unitaria del Value Object Email:
 * Verifica que el objeto nazca siempre validado (inmutabilidad y Fail-Fast)
 * tanto en escenarios válidos como frente a formatos corruptos.
 */
class EmailTest {

    @Test
    @DisplayName("Caso Feliz: Debería crear un Email válido y normalizarlo en minúsculas")
    void deberiaCrearEmailValido() {
        // Arrange & Act: Instanciamos el VO con mayúsculas y espacios laterales
        Email email = new Email("  Autor.RedSocial@Ejemplo.COM  ");

        // Assert: Comprobamos que se haya limpiado y almacenado correctamente
        assertEquals("autor.redsocial@ejemplo.com", email.getValue());
    }

    @Test
    @DisplayName("Caso de Error: Debería lanzar InvalidEmailException si el correo no tiene arroba ni dominio")
    void deberiaFallarConFormatoInvalido() {
        // Act & Assert: Verificamos que la regla atómica detenga la creación del objeto
        assertThrows(InvalidEmailException.class, () -> {
            new Email("correo-sin-arroba.com");
        });
    }
}