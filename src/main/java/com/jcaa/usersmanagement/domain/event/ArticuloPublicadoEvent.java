package com.jcaa.usersmanagement.domain.event;

import com.jcaa.usersmanagement.domain.model.ArticuloId;
import com.jcaa.usersmanagement.domain.model.UsuarioId;
import java.time.LocalDateTime;

/**
 * Evento de Dominio: Registra el hecho inmutable de que un artículo fue
 * creado y aprobado para ser visible en la red social.
 */
public final class ArticuloPublicadoEvent {
    private final ArticuloId articuloId;
    private final UsuarioId autorId;
    private final LocalDateTime ocurridoEn;

    public ArticuloPublicadoEvent(ArticuloId articuloId, UsuarioId autorId) {
        this.articuloId = articuloId;
        this.autorId = autorId;
        this.ocurridoEn = LocalDateTime.now();
    }

    public ArticuloId getArticuloId() { return articuloId; }
    public UsuarioId getAutorId() { return autorId; }
    public LocalDateTime getOcurridoEn() { return ocurridoEn; }
}