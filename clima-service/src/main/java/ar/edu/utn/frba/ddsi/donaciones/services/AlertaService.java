package ar.edu.utn.frba.ddsi.donaciones.services;

import ar.edu.utn.frba.ddsi.donaciones.models.entities.ClimaActual;
import ar.edu.utn.frba.ddsi.donaciones.models.repositories.ClimaActualRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AlertaService {

    private final ClimaActualRepository climaRepository;
    private final NotificacionesService notifcacionesService;


    public void analizarUltimoClima() {
        Optional<ClimaActual> optional = climaRepository.findUltimoByFechaConsulta();

        if(optional.isEmpty()) {
            return;
        }

        ClimaActual clima = optional.get();

        if(clima.getRevisado()){
            return;
        }

        if(clima.esCritico()) {
            notifcacionesService.notificarAlerta(clima);
        }

        clima.setRevisado(true);
        climaRepository.save(clima);
    }
}