package com.jcaa.usersmanagement.domain.port.out;

import com.jcaa.usersmanagement.domain.model.Articulo;
import com.jcaa.usersmanagement.domain.model.ArticuloId;
import java.util.Optional;

/**
 * Puerto de Salida (OUT - Driven Port):
 * Pertenece al núcleo del dominio y aplica el Principio de Inversión de Dependencias (DIP).
 */
public interface ArticuloRepositoryPort {
    
    // Contrato para persistir un Agregado Articulo completo y consistente
    void guardar(Articulo articulo);
    
    // Contrato para recuperar un Agregado usando su Value Object de identidad (ArticuloId)
    Optional<Articulo> buscarPorId(ArticuloId id);
}