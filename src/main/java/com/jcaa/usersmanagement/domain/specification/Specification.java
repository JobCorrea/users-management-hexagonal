package com.jcaa.usersmanagement.domain.specification;

/**
 * Patrón Specification: Define un contrato reutilizable para evaluar
 * reglas de negocio booleanas sobre objetos del dominio.
 */
public interface Specification<T> {
    boolean isSatisfiedBy(T candidate);
}