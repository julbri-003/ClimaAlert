package ar.edu.utn.frba.ddsi.donaciones;

import ar.edu.utn.frba.ddsi.donaciones.dto.ClimaData;
import ar.edu.utn.frba.ddsi.donaciones.models.entities.Provider.WeatherApiAdapter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class WeatherApiIntegrationTest {

    @Autowired
    private WeatherApiAdapter weatherApiAdapter;

    @Test
    void debeObtenerElClimaDesdeLaApi() {

        ClimaData clima = weatherApiAdapter.obtenerClimaActual();
        assertNotNull(clima);

        assertNotNull(clima.getCiudad());
        assertNotNull(clima.getPais());
        assertNotNull(clima.getCondicion());

        assertNotNull(clima.getTemperatura());
        assertNotNull(clima.getHumedad());

        assertTrue(clima.getTemperatura() > -100);
        assertTrue(clima.getHumedad() >= 0);
        assertTrue(clima.getHumedad() <= 100);


        String mensaje = """
                CLIMA DATA

                Ciudad: %s
                País: %s
                Temperatura: %.1f °C
                Humedad: %d%%
                Condición: %s
                """
                .formatted(
                        clima.getCiudad(),
                        clima.getPais(),
                        clima.getTemperatura(),
                        clima.getHumedad(),
                        clima.getCondicion()
                );


        System.out.println(mensaje);
    }
}