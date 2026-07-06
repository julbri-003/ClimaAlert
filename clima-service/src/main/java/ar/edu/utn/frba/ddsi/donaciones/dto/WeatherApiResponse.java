package ar.edu.utn.frba.ddsi.donaciones.dto;

import lombok.Data;

@Data
public class WeatherApiResponse {
    private Location location;
    private Current current;
}
