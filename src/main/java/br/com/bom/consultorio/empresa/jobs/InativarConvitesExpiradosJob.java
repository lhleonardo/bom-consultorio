package br.com.bom.consultorio.empresa.jobs;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
public class InativarConvitesExpiradosJob {

    @Scheduled(cron = "@midnight")
    public void inativarConvitesExpiradosJob() {}
}
