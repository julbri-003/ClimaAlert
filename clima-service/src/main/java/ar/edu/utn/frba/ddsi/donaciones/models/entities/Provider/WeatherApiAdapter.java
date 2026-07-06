package ar.edu.utn.frba.ddsi.donaciones.models.entities.Provider;

import ar.edu.utn.frba.ddsi.donaciones.dto.ClimaData;
import ar.edu.utn.frba.ddsi.donaciones.dto.WeatherApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class WeatherApiAdapter implements ClimaProvider {

    private final RestTemplate restTemplate;

    @Value("${weather.api.url}")
    private String apiUrl;

    @Value("${weather.api.key}")
    private String apiKey;

    @Value("${weather.location}")
    private String ubicacion;

    public WeatherApiAdapter() {
        this.restTemplate = new RestTemplate();
    }

    @Override
    public ClimaData obtenerClimaActual() {

        String url = String.format( "%s/current.json?key=%s&q=%s&lang=es", apiUrl, apiKey, ubicacion);

        WeatherApiResponse response = restTemplate.getForObject(url, WeatherApiResponse.class);

        if (response == null) {
            throw new RuntimeException("No se pudo obtener el clima");
        }

        return ClimaData.builder()
                .ciudad(response.getLocation().getName())
                .pais(response.getLocation().getCountry())
                .temperatura(response.getCurrent().getTempC())
                .condicion(response.getCurrent().getCondition().getText())
                .humedad(response.getCurrent().getHumidity())
                .build();
    }
}