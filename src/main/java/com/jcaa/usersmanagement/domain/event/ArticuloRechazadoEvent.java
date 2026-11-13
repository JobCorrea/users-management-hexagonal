package com.jcaa.usersmanagement.domain.event;

import com.jcaa.usersmanagement.domain.model.ArticuloId;
import java.time.LocalDateTime;

/**
 * Evento de Dominio: Notifica al sistema que un artículo cambió a estado
 * RECHAZADO y quedó bloqueado para nuevos comentarios.
 */
public final class ArticuloRechazadoEvent {
    private final ArticuloId articuloId;
    private final String motivo;
    private final LocalDateTime ocurridoEn;

    public ArticuloRechazadoEvent(ArticuloId articuloId, String motivo) {
        this.articuloId = articuloId;
        this.motivo = motivo;
        this.ocurridoEn = LocalDateTime.now();
    }

    public ArticuloId getArticuloId() { return articuloId; }
    public String getMotivo() { return motivo; }
    public LocalDateTime getOcurridoEn() { return ocurridoEn; }
}