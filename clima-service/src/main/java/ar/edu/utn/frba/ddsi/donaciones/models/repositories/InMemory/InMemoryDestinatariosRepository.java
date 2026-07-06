package ar.edu.utn.frba.ddsi.donaciones.models.repositories.InMemory;

import ar.edu.utn.frba.ddsi.donaciones.models.entities.Contacto.Contacto;
import ar.edu.utn.frba.ddsi.donaciones.models.entities.Destinatario;
import ar.edu.utn.frba.ddsi.donaciones.models.repositories.DestinatariosRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ar.edu.utn.frba.ddsi.donaciones.models.entities.Contacto.TipoDeContacto.EMAIL;

@Repository
public class InMemoryDestinatariosRepository implements DestinatariosRepository {
    private final List<Destinatario> destinatarios = new ArrayList<>();
    private Long nextId = 1L;

    public InMemoryDestinatariosRepository() {
        inicializarDestinatarios();
    }

    private void inicializarDestinatarios() {
        Contacto admin = new Contacto("admin@clima.com", EMAIL);
        Contacto emergencia = new Contacto("emergencias@clima.com", EMAIL);
        Contacto meteorologia = new Contacto("meteorologia@clima.com", EMAIL);

        Destinatario adminD= new Destinatario(1L, admin, true);
        Destinatario emergenciaD= new Destinatario(2L, emergencia, true);
        Destinatario meteorologiaD= new Destinatario(3L, meteorologia, true);

        save(adminD);

        save(emergenciaD);

        save(meteorologiaD);
    }


    @Override
    public Optional<List<Destinatario>> findAll() {
        return destinatarios.isEmpty() ? Optional.empty() : Optional.of(new ArrayList<>(destinatarios));
    }

    @Override
    public Optional<List<Destinatario>> findByActivoTrue() {
        List<Destinatario> activos = destinatarios.stream().filter(Destinatario::getActivo)
                .toList();

        return activos.isEmpty() ? Optional.empty() : Optional.of(activos);
    }

    @Override
    public Optional<Destinatario> findById(Long id) {
        return destinatarios.stream().filter(d -> d.getId().equals(id))
                .findFirst();
    }

    @Override
    public Destinatario save(Destinatario destinatario) {
        if (destinatario.getId() == null) {
            destinatario.setId(nextId++);
            destinatarios.add(destinatario);
        } else {
            delete(destinatario);
            destinatarios.add(destinatario);
        }
        return destinatario;
    }

    @Override
    public void delete(Destinatario destinatario) {
        destinatarios.removeIf(d -> d.getId().equals(destinatario.getId()));
    }
}
