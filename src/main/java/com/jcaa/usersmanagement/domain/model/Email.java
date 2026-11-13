package com.jcaa.usersmanagement.domain.model;

import com.jcaa.usersmanagement.domain.exception.InvalidEmailException;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Value Object Inmutable: Encapsula la dirección de correo de un usuario
 * y garantiza su validez sintáctica desde el momento de instanciación.
 */
public final class Email {
    private static final Pattern EMAIL_REGEX = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    private final String value;

    public Email(String value) {
        if (value == null || !EMAIL_REGEX.matcher(value).matches()) {
            throw new InvalidEmailException("El formato del correo electrónico no es válido: " + value);
        }
        this.value = value.toLowerCase().trim();
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email = (Email) o;
        return Objects.equals(value, email.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}