package ar.edu.utn.frba.ddsi.donaciones.schedulers;

import ar.edu.utn.frba.ddsi.donaciones.services.AlertaService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AlertaScheduler {

    private final AlertaService alertaService;

    @Scheduled(fixedRate = 60000)
    public void analizarClima() {
        alertaService.analizarUltimoClima();
    }

}
