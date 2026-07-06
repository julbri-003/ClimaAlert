package ar.edu.utn.frba.ddsi.donaciones.models.entities;

import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Data
public class ClimaActual {
    private Long id;
    private Double temperatura;
    private Integer humedad;
    private LocalDateTime fechaConsulta;
    private String pais;
    private String ciudad;
    private String condicion;
    private Boolean revisado = false;

    private Double tempCritica=35.0;
    private Integer humCritica=60;

    public ClimaActual(LocalDateTime fechaConsulta, String pais, String ciudad, Double temperatura, Integer humedad, String condicion, Boolean revisado) {
        this.fechaConsulta = fechaConsulta;
        this.pais = pais;
        this.ciudad = ciudad;
        this.condicion = condicion;
        this.revisado = revisado;
        this.temperatura = temperatura;
        this.humedad = humedad;
    }

    public Boolean esCritico(){
        boolean temperaturaCritica = getTemperatura() > tempCritica;
        boolean humedadCritica = getHumedad() > humCritica;

        return temperaturaCritica && humedadCritica;
    }
}
