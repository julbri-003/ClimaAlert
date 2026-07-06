package ar.edu.utn.frba.ddsi.donaciones.models.entities;

import ar.edu.utn.frba.ddsi.donaciones.models.entities.Contacto.Contacto;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Destinatario {
    private Long id;
    private Contacto contacto;
    private Boolean activo = true;
}
