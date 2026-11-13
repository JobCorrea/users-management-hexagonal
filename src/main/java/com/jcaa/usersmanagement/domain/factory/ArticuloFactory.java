package com.jcaa.usersmanagement.domain.factory;

import com.jcaa.usersmanagement.domain.model.Articulo;
import com.jcaa.usersmanagement.domain.model.ArticuloId;
import com.jcaa.usersmanagement.domain.model.TituloArticulo;
import com.jcaa.usersmanagement.domain.model.UsuarioId;

/**
 * Patrón Factory: Centraliza la construcción del Agregado Articulo y sus
 * Value Objects asociados, garantizando que nazca en un estado consistente.
 */
public class ArticuloFactory {
    public static Articulo crearArticulo(Long id, Long autorId, String titulo, String contenido) {
        return new Articulo(
            new ArticuloId(id),
            new UsuarioId(autorId),
            new TituloArticulo(titulo),
            contenido
        );
    }
}