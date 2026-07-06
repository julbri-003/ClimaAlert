package ar.edu.utn.frba.ddsi.donaciones.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ClimaData {

    private String ciudad;
    private String pais;
    private Double temperatura;
    private Integer humedad;
    private String condicion;
}