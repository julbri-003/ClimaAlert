package ar.edu.utn.frba.ddsi.donaciones.models.entities.Notificador;

import ar.edu.utn.frba.ddsi.donaciones.dto.ClimaAlerta;
import ar.edu.utn.frba.ddsi.donaciones.models.entities.Destinatario;

import java.util.List;

public interface Notificador {
    void enviarAlerta(ClimaAlerta alerta, List<Destinatario> destinatarios);
}
