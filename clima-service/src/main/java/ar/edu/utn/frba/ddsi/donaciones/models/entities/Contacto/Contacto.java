package ar.edu.utn.frba.ddsi.donaciones.models.entities.Contacto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Contacto {
    private String contacto;
    private TipoDeContacto tipo;
}
