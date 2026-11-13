package com.jcaa.usersmanagement.domain.model;

import java.util.Objects;

/**
 * Value Object de Identidad: Representa el identificador único de un Usuario.
 */
public final class UsuarioId {
    private final Long value;

    public UsuarioId(Long value) {
        if (value == null || value <= 0) {
            throw new IllegalArgumentException("El identificador del usuario debe ser mayor a cero.");
        }
        this.value = value;
    }

    public Long getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioId usuarioId = (UsuarioId) o;
        return Objects.equals(value, usuarioId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}