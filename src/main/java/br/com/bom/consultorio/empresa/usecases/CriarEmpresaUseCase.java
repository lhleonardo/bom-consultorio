package br.com.bom.consultorio.empresa.usecases;

import br.com.bom.consultorio.empresa.exceptions.EmpresaJaCadastradaParaCnpjException;
import br.com.bom.consultorio.empresa.exceptions.SlugEmpresaJaCadastradoException;
import br.com.bom.consultorio.empresa.models.EmpresaModel;
import br.com.bom.consultorio.empresa.repositories.EmpresaRepository;
import br.com.bom.consultorio.empresa.usecases.dto.CriarEmpresaUseCaseRequest;
import br.com.bom.consultorio.shared.http.context.EmpresaTenantContext;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.UUID;

@Log4j2
@Component
@RequiredArgsConstructor
public class CriarEmpresaUseCase {

    private final EmpresaRepository empresaRepository;

    /**
     * Criar uma nova empresa na plataforma, para atuar como uma agrupadora de informações. Todos os próximos
     * registros poderão ter o vínculo de "pertencem" a empresa.
     *
     * @param criarEmpresaRequest dados necessários para cadastrar uma nova empresa
     * @return model representativo da empresa
     */
    public EmpresaModel executar(@Valid CriarEmpresaUseCaseRequest criarEmpresaRequest) {
        log.info("Inserindo nova empresa: {}", criarEmpresaRequest);

        log.info("Empresa atual conectada: {}", EmpresaTenantContext.getEmpresaAtual());

        if (this.empresaRepository.existsByCnpj(criarEmpresaRequest.getCnpj())) {
            throw new EmpresaJaCadastradaParaCnpjException();
        }

        if (this.empresaRepository.existsBySlug(criarEmpresaRequest.getSlug())) {
            throw new SlugEmpresaJaCadastradoException();
        }

        EmpresaModel empresa = new EmpresaModel();

        empresa.setIdentificador(UUID.randomUUID().toString());
        empresa.setDataCriacao(OffsetDateTime.now());

        empresa.setCnpj(criarEmpresaRequest.getCnpj());
        empresa.setRazaoSocial(criarEmpresaRequest.getRazaoSocial());
        empresa.setNomeFantasia(criarEmpresaRequest.getNomeFantasia());
        empresa.setInscricaoEstadual(criarEmpresaRequest.getInscricaoEstadual());
        empresa.setSlug(criarEmpresaRequest.getSlug());

        return this.empresaRepository.save(empresa);
    }
}
