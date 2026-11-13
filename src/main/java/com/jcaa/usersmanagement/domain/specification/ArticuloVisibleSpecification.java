package com.jcaa.usersmanagement.domain.specification;

import com.jcaa.usersmanagement.domain.model.Articulo;

/**
 * Especificación Concreta: Determina si un artículo cumple las condiciones
 * para mostrarse en el muro público (activo y no nulo).
 */
public class ArticuloVisibleSpecification implements Specification<Articulo> {
    @Override
    public boolean isSatisfiedBy(Articulo articulo) {
        return articulo != null && !articulo.esRechazado();
    }
}