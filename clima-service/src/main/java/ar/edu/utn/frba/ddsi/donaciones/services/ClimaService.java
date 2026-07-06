package ar.edu.utn.frba.ddsi.donaciones.services;

import ar.edu.utn.frba.ddsi.donaciones.dto.ClimaData;
import ar.edu.utn.frba.ddsi.donaciones.models.entities.ClimaActual;

import ar.edu.utn.frba.ddsi.donaciones.models.entities.Provider.ClimaProvider;
import ar.edu.utn.frba.ddsi.donaciones.models.repositories.ClimaActualRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ClimaService {

    private final ClimaActualRepository climaRepository;
    private final ClimaProvider climaProvider;

    public ClimaActual obtenerYGuardarClima() {

        ClimaData datos = climaProvider.obtenerClimaActual();

        ClimaActual clima = new ClimaActual(
                LocalDateTime.now(),
                datos.getPais(),
                datos.getCiudad(),
                datos.getTemperatura(),
                datos.getHumedad(),
                datos.getCondicion(),
                false
        );

        return climaRepository.save(clima);
    }

}