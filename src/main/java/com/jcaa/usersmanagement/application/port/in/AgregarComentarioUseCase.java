package com.jcaa.usersmanagement.application.port.in;

/**
 * Puerto de Entrada (IN):
 * Expone la operación de negocio para añadir comentarios a un artículo existente.
 */
public interface AgregarComentarioUseCase {
    
    void agregarComentario(Long articuloId, String textoComentario);
}