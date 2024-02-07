package br.com.bom.consultorio.empresa.usecases.convites;

import br.com.bom.consultorio.empresa.models.convite.ConviteEmpresaModel;
import br.com.bom.consultorio.empresa.models.convite.StatusConviteEnum;
import br.com.bom.consultorio.empresa.repositories.ConviteEmpresaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.OffsetDateTime;
import java.util.List;

@Log4j2
@Component
@RequiredArgsConstructor
public class DesativarConvitesExpiradosUseCase {

    private final ConviteEmpresaRepository conviteEmpresaRepository;
    private final Clock clock;

    @Transactional
    public void executar() {
        log.info("Iniciando desativação de convites expirados...");

        OffsetDateTime dataAtual = OffsetDateTime.now(this.clock);
        List<ConviteEmpresaModel> convitesPendentes = this.conviteEmpresaRepository
                .findByStatusAndDataExpiracaoBefore(StatusConviteEnum.PENDENTE, dataAtual);

        log.info("Quantidade de convites expirados: {}", convitesPendentes.size());
        convitesPendentes.parallelStream().forEach(this::inativarConvites);

        log.info("Processo de desativação finalizado!");
    }

    private void inativarConvites(ConviteEmpresaModel conviteEmpresaModel) {
        conviteEmpresaModel.expirar();
        this.conviteEmpresaRepository.save(conviteEmpresaModel);
    }
}
