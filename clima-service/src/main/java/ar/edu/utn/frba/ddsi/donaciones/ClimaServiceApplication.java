package ar.edu.utn.frba.ddsi.donaciones;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@EnableRetry
public class ClimaServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ClimaServiceApplication.class, args);
    }
}
