package br.com.bom.consultorio.empresa.services;

import br.com.bom.consultorio.empresa.models.EmpresaModel;
import br.com.bom.consultorio.empresa.payloads.requests.CriarEmpresaApiRequest;
import br.com.bom.consultorio.empresa.usecases.ConsultarEmpresaPorSlugUseCase;
import br.com.bom.consultorio.empresa.usecases.CriarEmpresaUseCase;
import br.com.bom.consultorio.empresa.usecases.dto.CriarEmpresaUseCaseRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service para gerenciamento das empresas/tenants da plataforma. Aqui serão feitos os cadastros
 * gerenciados pelos administradores do SaaS, possibilitando a inserção manual de empresas na plataforma.
 *
 */
@Log4j2
@Service
@RequiredArgsConstructor
public class EmpresaService {

    private final CriarEmpresaUseCase criarEmpresaUseCase;
    private final ConsultarEmpresaPorSlugUseCase consultarEmpresaPorSlugUseCase;

    /**
     * Criar uma nova empresa para utilização como tenant
     * @param criarEmpresaApiRequest dados cadastrais
     * @return model da empresa criada
     */
    public EmpresaModel criarEmpresa(CriarEmpresaApiRequest criarEmpresaApiRequest) {
        CriarEmpresaUseCaseRequest useCaseRequest = CriarEmpresaUseCaseRequest
                .builder()
                .slug(criarEmpresaApiRequest.getSlug())
                .inscricaoEstadual(criarEmpresaApiRequest.getInscricaoEstadual())
                .nomeFantasia(criarEmpresaApiRequest.getNomeFantasia())
                .razaoSocial(criarEmpresaApiRequest.getRazaoSocial())
                .cnpj(criarEmpresaApiRequest.getCnpj())
                .build();

        return this.criarEmpresaUseCase.executar(useCaseRequest);
    }

    public Optional<EmpresaModel> consultarEmpresaPorSlugUseCase(String slug) {
        return this.consultarEmpresaPorSlugUseCase.executar(slug);
    }
}
