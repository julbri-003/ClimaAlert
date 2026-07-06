package ar.edu.utn.frba.ddsi.donaciones.models.repositories.InMemory;

import ar.edu.utn.frba.ddsi.donaciones.models.entities.ClimaActual;
import ar.edu.utn.frba.ddsi.donaciones.models.repositories.ClimaActualRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryClimaActualRepository implements ClimaActualRepository {
    private final List<ClimaActual> climas = new ArrayList<>();
    private Long nextId = 1L;

    @Override
    public Optional<ClimaActual> findUltimoByFechaConsulta() {
        return climas.stream()
                .max(Comparator.comparing(ClimaActual::getFechaConsulta));
    }

    @Override
    public Optional<List<ClimaActual>> findAll() {
        return climas.isEmpty() ? Optional.empty() : Optional.of(new ArrayList<>(climas));
    }

    @Override
    public Optional<ClimaActual> findById(Long id) {
        return climas.stream().filter(c -> c.getId().equals(id))
                .findFirst();
    }

    @Override
    public ClimaActual save(ClimaActual climaActual) {
        if (climaActual.getId() == null) {
            climaActual.setId(nextId++);
            climas.add(climaActual);
        } else {
            delete(climaActual);
            climas.add(climaActual);
        }

        return climaActual;
    }

    @Override
    public void delete(ClimaActual climaActual) {
        climas.removeIf(c -> c.getId().equals(climaActual.getId()));
    }

}
