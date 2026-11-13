package com.jcaa.usersmanagement.domain.exception;

/**
 * Excepción de Dominio: Se lanza cuando un correo electrónico no cumple
 * con las reglas de formato exigidas por el Value Object Email.
 */
public class InvalidEmailException extends RuntimeException {
    public InvalidEmailException(String message) {
        super(message);
    }
}