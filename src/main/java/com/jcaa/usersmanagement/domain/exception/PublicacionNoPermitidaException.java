package com.jcaa.usersmanagement.domain.exception;

/**
 * Excepción de Dominio: Disparada por el Servicio de Dominio cuando un texto
 * infringe las normas de moderación o contiene términos prohibidos.
 */
public class PublicacionNoPermitidaException extends RuntimeException {
    public PublicacionNoPermitidaException(String message) {
        super(message);
    }
}