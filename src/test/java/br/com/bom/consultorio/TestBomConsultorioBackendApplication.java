package br.com.bom.consultorio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestBomConsultorioBackendApplication {

    public static void main(String[] args) {
        SpringApplication.from(BomConsultorioBackendApplication::main).with(TestBomConsultorioBackendApplication.class).run(args);
    }

}
