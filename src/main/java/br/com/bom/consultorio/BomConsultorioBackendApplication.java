package br.com.bom.consultorio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class BomConsultorioBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BomConsultorioBackendApplication.class, args);
    }

}
