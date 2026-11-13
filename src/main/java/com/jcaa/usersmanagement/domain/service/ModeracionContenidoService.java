package com.jcaa.usersmanagement.domain.service;

import com.jcaa.usersmanagement.domain.exception.PublicacionNoPermitidaException;
import java.util.List;

/**
 * Servicio de Dominio: Encapsula políticas transversales de moderación
 * que aplican tanto a artículos como a comentarios sin acoplarse a ninguna entidad.
 */
public class ModeracionContenidoService {

    private static final List<String> PALABRAS_PROHIBIDAS = List.of("spam", "estafa", "ofensivo", "ilegal");

    public void validarContenido(String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            throw new PublicacionNoPermitidaException("El texto de la publicación no puede estar vacío.");
        }
        String textoNormalizado = texto.toLowerCase();
        for (String palabra : PALABRAS_PROHIBIDAS) {
            if (textoNormalizado.contains(palabra)) {
                throw new PublicacionNoPermitidaException("Contenido bloqueado por infringir normas: " + palabra);
            }
        }
    }
}