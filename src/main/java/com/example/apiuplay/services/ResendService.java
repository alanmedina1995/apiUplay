package com.example.apiuplay.services;

import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.SendEmailRequest;
import org.springframework.stereotype.Service;

@Service
public class ResendService {

    private final Resend resend = new Resend("re_iK3SYRw9_7YoioW2qJkKj5pbBkffotrgb");

    public void sendMailRegister(String name) {

        SendEmailRequest sendEmailRequest = SendEmailRequest.builder()
                .from("Uplay <onboarding@resend.dev>")
                .to("alanmedina1995@gmail.com")
                .subject("¡Bienvenido a Uplay!")
                .html("<p>¡Hola " + name + "!," +
                        "<br/><br/>Bienvenido a Uplay, tu destino para información cripto en tiempo real y una plataforma de juegos emocionantes diseñada para ayudarte a aumentar tu capital!" +
                        "<br/><br/>Nos emociona tenerte como parte de nuestra comunidad. Al registrarte, has recibido un generoso bono de 50000 UTN Coin, una moneda interna que puedes utilizar para explorar nuestra plataforma, jugar y aumentar tus fondos." +
                        "<br/><br/>En Uplay, te ofrecemos herramientas y recursos actualizados para tomar decisiones informadas en el mundo de las criptomonedas, y también la diversión y emoción de nuestros juegos, donde puedes ganar aún más capital." +
                        "<br/><br/>Estamos comprometidos a proporcionarte una experiencia única y valiosa en Uplay. Aprovecha al máximo nuestros recursos, interactúa con nuestra comunidad y disfruta de tu viaje hacia el éxito financiero." +
                        "<br/><br/>¡Estamos encantados de tener tu presencia en Uplay!" +
                        "<br/><br/>¡Saludos cordiales del equipo Uplay!</p>")
                .build();

        try {
            resend.emails().send(sendEmailRequest);
        } catch (ResendException e) {
            e.printStackTrace();
        }
    }

    public void sendMailModifyPassword(String name) {

        SendEmailRequest sendEmailRequest = SendEmailRequest.builder()
                .from("Uplay <onboarding@resend.dev>")
                .to("alanmedina1995@gmail.com")
                .subject("Novedades sobre tu cuenta Uplay.")
                .html("<p>¡Hola " + name + "!," +
                        "<br/><br/>Hemos detectado un cambio reciente en tu contraseña en Uplay. Si no realizaste esta modificación, te recomendamos contactar de inmediato con nuestro servicio de soporte en soporte@uplay.com para investigar y asegurar la seguridad de tu cuenta." +
                        "<br/><br/>Es fundamental garantizar la protección de tu información y cuenta en Uplay. Nuestro equipo está aquí para ayudarte en caso de cualquier inconveniente o duda." +
                        "<br/><br/>¡Estamos comprometidos a proporcionarte una experiencia segura y satisfactoria en Uplay!" +
                        "<br/><br/>Estamos comprometidos a proporcionarte una experiencia única y valiosa en Uplay. Aprovecha al máximo nuestros recursos, interactúa con nuestra comunidad y disfruta de tu viaje hacia el éxito financiero." +
                        "<br/><br/>¡Saludos cordiales del equipo Uplay!</p>")
                .build();

        try {
            resend.emails().send(sendEmailRequest);
        } catch (ResendException e) {
            e.printStackTrace();
        }
    }
}
