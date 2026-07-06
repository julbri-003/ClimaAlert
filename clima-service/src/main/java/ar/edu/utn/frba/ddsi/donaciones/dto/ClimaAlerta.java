package ar.edu.utn.frba.ddsi.donaciones.dto;

import ar.edu.utn.frba.ddsi.donaciones.models.entities.ClimaActual;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class ClimaAlerta {

    private String ciudad;
    private String pais;
    private Double temperatura;
    private Integer humedad;
    private String condicion;
    private LocalDateTime fechaAlerta;

    public static ClimaAlerta construirCon(ClimaActual clima) {

        return ClimaAlerta.builder()
                .ciudad(clima.getCiudad())
                .pais(clima.getPais())
                .temperatura(clima.getTemperatura())
                .humedad(clima.getHumedad())
                .fechaAlerta(LocalDateTime.now())
                .build();
    }
}