package ar.edu.utn.frba.ddsi.donaciones.models.entities.Notificador;

import ar.edu.utn.frba.ddsi.donaciones.dto.ClimaAlerta;
import ar.edu.utn.frba.ddsi.donaciones.models.entities.Destinatario;
import ar.edu.utn.frba.ddsi.donaciones.models.entities.Notificador.ProveedorEmail.ProveedorEmail;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class EmailAdapter implements Notificador {

    private final ProveedorEmail proveedorEmail;

    @Override
    public void enviarAlerta(ClimaAlerta alerta, List<Destinatario> destinatarios) {

        String mensaje = construirMensaje(alerta);

        proveedorEmail.enviarEmail(
                "ALERTA CLIMÁTICA",
                mensaje,
                destinatarios.stream()
                        .map(d->d.getContacto().getContacto())
                        .toList()
        );
    }

    private String construirMensaje(ClimaAlerta alerta) {

        return """
                ALERTA CLIMÁTICA

                Se detectaron condiciones climáticas críticas.

                Ciudad: %s
                País: %s
                Temperatura: %.1f °C
                Humedad: %d%%
                Condición: %s
                Fecha: %s
                """
                .formatted(
                        alerta.getCiudad(),
                        alerta.getPais(),
                        alerta.getTemperatura(),
                        alerta.getHumedad(),
                        alerta.getCondicion(),
                        alerta.getFechaAlerta()
                );
    }
}