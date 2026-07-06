package ar.edu.utn.frba.ddsi.donaciones.models.entities.Notificador.ProveedorEmail;

import org.springframework.stereotype.Component;

import java.util.List;
import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class TwilioAdapter implements ProveedorEmail {

    @Value("${email.remitente}")
    private String remitente;

    @Value("${twilio.api.key}")
    private String apiKey;

    public void enviarEmail(String asunto, String mensaje, List<String> contactos) {
        Email from = new Email(remitente);
        Content content = new Content("text/plain", mensaje);
        Mail mail = new Mail();
        mail.setFrom(from);
        mail.setSubject(asunto);

        Personalization personalization = new Personalization();
        for (String contacto : contactos) {
            personalization.addTo(new Email(contacto));
        }
        mail.addPersonalization(personalization);
        mail.addContent(content);

        SendGrid sg = new SendGrid(apiKey);
        Request request = new Request();

        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());

            Response response = sg.api(request);

            if (response.getStatusCode() >= 200 && response.getStatusCode() < 300) {
                System.out.println("Email enviado exitosamente.");
            } else {
                System.err.println("Error al enviar email: " + response.getBody());
            }
        } catch (IOException ex) {
            throw new RuntimeException("Error de conexión con Twilio", ex);
        }
    }
}
