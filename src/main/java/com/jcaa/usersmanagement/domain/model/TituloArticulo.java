package com.jcaa.usersmanagement.domain.model;

import java.util.Objects;

/**
 * Value Object Inmutable: Garantiza que el título de toda publicación cumpla
 * con la longitud mínima y máxima establecida por el negocio.
 */
public final class TituloArticulo {
    private final String value;

    public TituloArticulo(String value) {
        if (value == null || value.trim().length() < 5 || value.trim().length() > 120) {
            throw new IllegalArgumentException("El título del artículo debe tener entre 5 y 120 caracteres.");
        }
        this.value = value.trim();
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TituloArticulo that = (TituloArticulo) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}