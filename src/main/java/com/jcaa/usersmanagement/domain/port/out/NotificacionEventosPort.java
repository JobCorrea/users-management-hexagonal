package com.jcaa.usersmanagement.domain.port.out;

/**
 * Puerto de Salida (OUT):
 * Abstrae el mecanismo de mensajería o notificación externa. Permite que la capa de
 * aplicación despache los Eventos de Dominio.
 */
public interface NotificacionEventosPort {
    
    // Recibe cualquier evento ocurrido en el dominio para propagarlo al exterior
    void publicarEvento(Object eventoDominio);
}