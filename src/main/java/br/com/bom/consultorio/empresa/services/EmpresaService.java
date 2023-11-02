package br.com.bom.consultorio.empresa.services;

import br.com.bom.consultorio.empresa.exceptions.EmpresaJaCadastradaParaCnpjException;
import br.com.bom.consultorio.empresa.models.EmpresaModel;
import br.com.bom.consultorio.empresa.repositories.EmpresaRepository;
import br.com.bom.consultorio.empresa.requests.CriarEmpresaRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * Service para gerenciamento das empresas/tenants da plataforma. Aqui serão feitos os cadastros
 * gerenciados pelos administradores do SaaS, possibilitando a inserção manual de empresas na plataforma.
 *
 */
@Log4j2
@Service
@RequiredArgsConstructor
public class EmpresaService {

    private final EmpresaRepository empresaRepository;

    /**
     * Criar uma nova empresa para utilização como tenant
     * @param criarEmpresaRequest dados cadastrais
     * @return model da empresa criada
     */
    @Transactional
    public EmpresaModel criarEmpresa(CriarEmpresaRequest criarEmpresaRequest) {

        log.info("Inserindo nova empresa: {}", criarEmpresaRequest);

        if (this.empresaRepository.findByCnpj(criarEmpresaRequest.getCnpj()).isPresent()) {
            throw new EmpresaJaCadastradaParaCnpjException();
        }

        EmpresaModel empresa = new EmpresaModel();

        empresa.setIdentificador(UUID.randomUUID().toString());
        empresa.setDataCriacao(OffsetDateTime.now());

        empresa.setCnpj(criarEmpresaRequest.getCnpj());
        empresa.setRazaoSocial(criarEmpresaRequest.getRazaoSocial());
        empresa.setNomeFantasia(criarEmpresaRequest.getNomeFantasia());
        empresa.setInscricaoEstadual(empresa.getInscricaoEstadual());

        return this.empresaRepository.save(empresa);
    }
}
