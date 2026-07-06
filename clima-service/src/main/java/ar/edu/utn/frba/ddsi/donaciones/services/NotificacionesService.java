package ar.edu.utn.frba.ddsi.donaciones.services;

import ar.edu.utn.frba.ddsi.donaciones.dto.ClimaAlerta;
import ar.edu.utn.frba.ddsi.donaciones.models.entities.ClimaActual;
import ar.edu.utn.frba.ddsi.donaciones.models.entities.Destinatario;
import ar.edu.utn.frba.ddsi.donaciones.models.entities.Notificador.Notificador;
import ar.edu.utn.frba.ddsi.donaciones.models.repositories.DestinatariosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificacionesService {

    private final Notificador notificador;
    private final DestinatariosRepository destinatarioRepository;

    public NotificacionesService(
            Notificador notificador,
            DestinatariosRepository destinatarioRepository) {
        this.notificador = notificador;
        this.destinatarioRepository = destinatarioRepository;
    }

    public void notificarAlerta(ClimaActual clima) {

        List<Destinatario> destinatarios = destinatarioRepository.findByActivoTrue().orElse(List.of());

        ClimaAlerta alerta = ClimaAlerta.construirCon(clima);

        notificador.enviarAlerta(alerta, destinatarios);
    }
}