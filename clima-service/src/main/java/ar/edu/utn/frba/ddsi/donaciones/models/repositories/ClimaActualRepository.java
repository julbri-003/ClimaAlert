package ar.edu.utn.frba.ddsi.donaciones.models.repositories;

import ar.edu.utn.frba.ddsi.donaciones.models.entities.ClimaActual;

import java.util.List;
import java.util.Optional;

public interface ClimaActualRepository {
    Optional<ClimaActual> findUltimoByFechaConsulta();

    Optional<List<ClimaActual>> findAll();

    Optional<ClimaActual> findById(Long id);

    ClimaActual save(ClimaActual climaActual);

    void delete(ClimaActual climaActual);
}
