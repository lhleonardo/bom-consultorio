package br.com.bom.consultorio.empresa.jobs;

import br.com.bom.consultorio.empresa.usecases.convites.DesativarConvitesExpiradosUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
public class DesativarConvitesExpiradosJob {

    private final DesativarConvitesExpiradosUseCase desativarConvitesExpiradosUseCase;

    @Scheduled(cron = "@midnight")
    public void inativarConvitesExpiradosJob() {
        log.info("Iniciando job para desativar convites");
        this.desativarConvitesExpiradosUseCase.executar();
        log.info("Job finalizado!");
    }
}
