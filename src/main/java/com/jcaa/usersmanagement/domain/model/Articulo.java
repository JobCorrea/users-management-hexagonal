package com.jcaa.usersmanagement.domain.model;

import com.jcaa.usersmanagement.domain.event.ArticuloPublicadoEvent;
import com.jcaa.usersmanagement.domain.event.ArticuloRechazadoEvent;
import com.jcaa.usersmanagement.domain.exception.ArticuloRechazadoException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Raíz de Agregado (Aggregate Root): Modela la publicación principal y actúa
 * como guardián de sus invariantes de estado y su colección de comentarios.
 */
public class Articulo {
    private final ArticuloId id;
    private final UsuarioId autorId;
    private final TituloArticulo titulo;
    private final String contenido;
    private boolean rechazado;
    private final List<String> comentarios;
    private final List<Object> eventosDominio;

    public Articulo(ArticuloId id, UsuarioId autorId, TituloArticulo titulo, String contenido) {
        this.id = Objects.requireNonNull(id, "El ID del artículo es obligatorio");
        this.autorId = Objects.requireNonNull(autorId, "El autor del artículo es obligatorio");
        this.titulo = Objects.requireNonNull(titulo, "El título del artículo es obligatorio");
        this.contenido = Objects.requireNonNull(contenido, "El contenido no puede ser nulo");
        this.rechazado = false;
        this.comentarios = new ArrayList<>();
        this.eventosDominio = new ArrayList<>();
        
        // Registramos el evento de dominio al nacer el agregado
        this.eventosDominio.add(new ArticuloPublicadoEvent(this.id, this.autorId));
    }

    /**
     * Regla de negocio: Marca el artículo como rechazado y registra el evento correspondiente.
     */
    public void rechazar(String motivo) {
        this.rechazado = true;
        this.eventosDominio.add(new ArticuloRechazadoEvent(this.id, motivo));
    }

    /**
     * Invariante crítica: Impide añadir comentarios si el artículo fue rechazado.
     */
    public void agregarComentario(String comentario) {
        if (this.rechazado) {
            throw new ArticuloRechazadoException("No se pueden agregar comentarios a un artículo rechazado.");
        }
        if (comentario == null || comentario.trim().isEmpty()) {
            throw new IllegalArgumentException("El comentario no puede estar vacío.");
        }
        this.comentarios.add(comentario.trim());
    }

    // Encapsulamiento: Devuelve vista de solo lectura para proteger la lista interna
    public List<String> getComentarios() {
        return Collections.unmodifiableList(comentarios);
    }

    public List<Object> getEventosDominio() {
        return Collections.unmodifiableList(eventosDominio);
    }

    public ArticuloId getId() { return id; }
    public UsuarioId getAutorId() { return autorId; }
    public TituloArticulo getTitulo() { return titulo; }
    public String getContenido() { return contenido; }
    public boolean esRechazado() { return rechazado; }
}