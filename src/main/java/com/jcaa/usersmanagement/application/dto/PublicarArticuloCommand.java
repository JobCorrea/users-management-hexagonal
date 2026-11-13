package com.jcaa.usersmanagement.application.dto;

/**
 * DTO de Entrada (Command):
 * Su función es actuar como un paquete de datos plano e inmutable (usando 'record' de Java)
 * que transporta la información cruda capturada desde el exterior (consola, interfaz gráfica o API)
 * hacia la capa de aplicación.
 */
public record PublicarArticuloCommand(
    Long id,          
    Long autorId,     
    String titulo,    
    String contenido  
) {}