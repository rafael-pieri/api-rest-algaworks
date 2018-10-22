package com.algaworks.socialbooks.dto.book;

import java.time.ZonedDateTime;
import java.util.UUID;

public class BookCreateDTO {

    private UUID id;
    private ZonedDateTime lastModified;

    public BookCreateDTO(final UUID id, final ZonedDateTime lastModified) {
        this.id = id;
        this.lastModified = lastModified;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public ZonedDateTime getLastModified() {
        return lastModified;
    }

    public void setLastModified(ZonedDateTime lastModified) {
        this.lastModified = lastModified;
    }
}