package ar.edu.utn.frba.ddsi.donaciones.models.entities.Provider;

import ar.edu.utn.frba.ddsi.donaciones.dto.ClimaData;
import org.springframework.stereotype.Component;

@Component
public interface ClimaProvider {
    public ClimaData obtenerClimaActual();
}
