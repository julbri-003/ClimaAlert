package ar.edu.utn.frba.ddsi.donaciones;

import ar.edu.utn.frba.ddsi.donaciones.dto.ClimaAlerta;
import ar.edu.utn.frba.ddsi.donaciones.models.entities.ClimaActual;
import ar.edu.utn.frba.ddsi.donaciones.models.entities.Contacto.Contacto;
import ar.edu.utn.frba.ddsi.donaciones.models.entities.Destinatario;
import ar.edu.utn.frba.ddsi.donaciones.models.entities.Notificador.Notificador;
import ar.edu.utn.frba.ddsi.donaciones.models.repositories.ClimaActualRepository;
import ar.edu.utn.frba.ddsi.donaciones.models.repositories.DestinatariosRepository;
import ar.edu.utn.frba.ddsi.donaciones.services.AlertaService;
import ar.edu.utn.frba.ddsi.donaciones.services.NotificacionesService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static ar.edu.utn.frba.ddsi.donaciones.models.entities.Contacto.TipoDeContacto.EMAIL;
import static org.mockito.Mockito.*;
import static org.springframework.test.util.AssertionErrors.assertTrue;

@ExtendWith(MockitoExtension.class)
class AlertaServiceTest {

    @Mock
    private ClimaActualRepository climaRepository;

    @Mock
    private NotificacionesService notificacionesService;

    @Mock
    private DestinatariosRepository destinatarioRepository;

    @Mock
    private Notificador notificador;

    @InjectMocks
    private AlertaService alertaService;

    @Test
    void debeGenerarAlertaCuandoElClimaEsCritico() {

        ClimaActual clima = new ClimaActual(
                LocalDateTime.now(),
                "Arg",
                "CABA",
                38.0,
                75,
                "Parcialmente nublado",
                false
                );

        when(climaRepository.findUltimoByFechaConsulta()).thenReturn(Optional.of(clima));

        alertaService.analizarUltimoClima();

        verify(notificacionesService, times(1)).notificarAlerta(clima);

        verify(climaRepository, times(1)).save(clima);

        assertTrue("Se envio alerta" , clima.getRevisado());
    }

    @Test
    void noDebeGenerarAlertaCuandoElClimaNoEsCritico() {

        ClimaActual clima = new ClimaActual(
                LocalDateTime.now(),
                "Arg",
                "CABA",
                25.0,
                50,
                "Parcialmente nublado",
                false
        );

        when(climaRepository.findUltimoByFechaConsulta()).thenReturn(Optional.of(clima));

        alertaService.analizarUltimoClima();

        verify(notificacionesService, never()).notificarAlerta(any());

        verify(climaRepository, times(1)).save(clima);
    }

}

