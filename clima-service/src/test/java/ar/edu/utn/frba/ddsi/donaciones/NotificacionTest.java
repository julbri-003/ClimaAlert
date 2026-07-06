package ar.edu.utn.frba.ddsi.donaciones;

import ar.edu.utn.frba.ddsi.donaciones.dto.ClimaAlerta;
import ar.edu.utn.frba.ddsi.donaciones.models.entities.ClimaActual;
import ar.edu.utn.frba.ddsi.donaciones.models.entities.Contacto.Contacto;
import ar.edu.utn.frba.ddsi.donaciones.models.entities.Destinatario;
import ar.edu.utn.frba.ddsi.donaciones.models.entities.Notificador.Notificador;
import ar.edu.utn.frba.ddsi.donaciones.models.repositories.DestinatariosRepository;
import ar.edu.utn.frba.ddsi.donaciones.services.NotificacionesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static ar.edu.utn.frba.ddsi.donaciones.models.entities.Contacto.TipoDeContacto.EMAIL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class NotificacionTest {

    private NotificacionesService notificacionesService;

    @Mock
    private DestinatariosRepository destinatarioRepository;

    @Mock
    private Notificador notificador;

    @BeforeEach
    void setUp() {
        notificacionesService = new NotificacionesService(notificador, destinatarioRepository);
    }

    @Test
    void debeEnviarLaAlertaATodosLosDestinatariosActivos() {
        ClimaActual clima = new ClimaActual(
                LocalDateTime.now(),
                "Arg",
                "CABA",
                38.0,
                70,
                "Parcialmente nublado",
                false
        );

        Contacto admin = new Contacto("admin@clima.com", EMAIL);
        Contacto emergencia = new Contacto("emergencias@clima.com", EMAIL);
        Contacto meteorologia = new Contacto("meteorologia@clima.com", EMAIL);

        List<Destinatario> destinatarios = List.of(
                new Destinatario(1L, admin, true),
                new Destinatario(2L, emergencia, true),
                new Destinatario(3L, meteorologia, true)
        );

        when(destinatarioRepository.findByActivoTrue())
                .thenReturn(Optional.of(destinatarios));

        notificacionesService.notificarAlerta(clima);

        ArgumentCaptor<ClimaAlerta> captor = ArgumentCaptor.forClass(ClimaAlerta.class);

        verify(notificador).enviarAlerta(captor.capture(), eq(destinatarios));

        ClimaAlerta alertaEnviada = captor.getValue();

        assertEquals(38.0, alertaEnviada.getTemperatura());
        assertEquals(70, alertaEnviada.getHumedad());
        assertEquals("CABA", alertaEnviada.getCiudad());

        verify(destinatarioRepository, times(1))
                .findByActivoTrue();
    }
}
