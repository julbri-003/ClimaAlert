package ar.edu.utn.frba.ddsi.donaciones.models.repositories;

import ar.edu.utn.frba.ddsi.donaciones.models.entities.Destinatario;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface DestinatariosRepository {
    Optional<List<Destinatario>> findAll();

    Optional<List<Destinatario>> findByActivoTrue();

    Optional<Destinatario> findById(Long id);

    Destinatario save(Destinatario destinatario);

    void delete(Destinatario destinatario);
}
