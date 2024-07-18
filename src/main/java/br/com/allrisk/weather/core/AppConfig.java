package br.com.allrisk.weather.core;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Validated
@Component
@ConfigurationProperties("weather.config")
public class AppConfig {

    @NotBlank
    private String apiKey;

    @NotBlank
    private String decryptKey;

}
