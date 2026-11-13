package com.jcaa.usersmanagement.domain.exception;

/**
 * Excepción de Dominio: Protege la invariante del Agregado Articulo,
 * impidiendo agregar comentarios cuando la publicación está rechazada.
 */
public class ArticuloRechazadoException extends RuntimeException {
    public ArticuloRechazadoException(String message) {
        super(message);
    }
}