package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.dto.PublicarArticuloCommand;
import com.jcaa.usersmanagement.application.port.in.PublicarArticuloUseCase;
import com.jcaa.usersmanagement.domain.factory.ArticuloFactory;
import com.jcaa.usersmanagement.domain.model.Articulo;
import com.jcaa.usersmanagement.domain.port.out.ArticuloRepositoryPort;
import com.jcaa.usersmanagement.domain.port.out.NotificacionEventosPort;
import com.jcaa.usersmanagement.domain.service.ModeracionContenidoService;

/**
 * Servicio de Aplicación (Implementación del Caso de Uso):
 * Orquesta el flujo completo conectando el Servicio de Dominio, la Fábrica y los Puertos de Salida.
 */
public class PublicarArticuloService implements PublicarArticuloUseCase {

    private final ModeracionContenidoService moderacionService;
    private final ArticuloRepositoryPort repositoryPort;
    private final NotificacionEventosPort eventosPort;

    public PublicarArticuloService(
            ModeracionContenidoService moderacionService,
            ArticuloRepositoryPort repositoryPort,
            NotificacionEventosPort eventosPort) {
        this.moderacionService = moderacionService;
        this.repositoryPort = repositoryPort;
        this.eventosPort = eventosPort;
    }

    @Override
    public void ejecutar(PublicarArticuloCommand command) {
        // 1. Auditoría de políticas globales mediante el Servicio de Dominio
        moderacionService.validarContenido(command.titulo());
        moderacionService.validarContenido(command.contenido());

        // 2. Transformación a modelo de dominio mediante el Patrón Factory
        Articulo articulo = ArticuloFactory.crearArticulo(
            command.id(),
            command.autorId(),
            command.titulo(),
            command.contenido()
        );

        // 3. Persistencia desacoplada a través del Puerto de Salida OUT
        repositoryPort.guardar(articulo);

        // 4. Propagación de Eventos de Dominio
        articulo.getEventosDominio().forEach(eventosPort::publicarEvento);
    }
}