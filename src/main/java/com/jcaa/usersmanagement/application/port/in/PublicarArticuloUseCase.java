package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.dto.PublicarArticuloCommand;

/**
 * Puerto de Entrada (IN - Driving Port):
 * Define el contrato público de lo que nuestra aplicación sabe hacer respecto a la
 * publicación de artículos.

 */
public interface PublicarArticuloUseCase {
    
    /**
     * Recibe el comando con los datos de entrada y dispara
     * toda la  validación, creación y guardado del artículo.
     */
    void ejecutar(PublicarArticuloCommand command);
}