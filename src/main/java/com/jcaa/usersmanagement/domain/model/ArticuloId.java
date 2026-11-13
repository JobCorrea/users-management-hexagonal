package com.jcaa.usersmanagement.domain.model;

import java.util.Objects;

/**
 * Value Object de Identidad: Evita el uso de tipos primitivos genéricos (Long)
 * para identificar de forma segura un Artículo dentro del dominio.
 */
public final class ArticuloId {
    private final Long value;

    public ArticuloId(Long value) {
        if (value == null || value <= 0) {
            throw new IllegalArgumentException("El identificador del artículo debe ser mayor a cero.");
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
        ArticuloId that = (ArticuloId) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}