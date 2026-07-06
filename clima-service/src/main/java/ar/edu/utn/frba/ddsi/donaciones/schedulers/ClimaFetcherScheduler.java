package ar.edu.utn.frba.ddsi.donaciones.schedulers;

import ar.edu.utn.frba.ddsi.donaciones.services.ClimaService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClimaFetcherScheduler {

    private final ClimaService climaService;

    @Scheduled(fixedRate = 300000)
    public void actualizarClima() {
        climaService.obtenerYGuardarClima();
    }
}