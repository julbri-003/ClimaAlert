package ar.edu.utn.frba.ddsi.donaciones.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
public class Current {

    @JsonProperty("temp_c")
    private Double tempC;
    private Integer humidity;
    private Condition condition;
}