package ar.edu.utn.frba.ddsi.donaciones.models.entities.Notificador.ProveedorEmail;

import java.util.List;

public interface ProveedorEmail {
    public void enviarEmail(String asunto, String mensaje, List<String> contactos);
}
