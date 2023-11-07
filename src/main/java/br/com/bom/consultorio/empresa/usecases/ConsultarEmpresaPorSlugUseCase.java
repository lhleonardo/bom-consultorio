package br.com.bom.consultorio.empresa.usecases;

import br.com.bom.consultorio.empresa.models.EmpresaModel;
import br.com.bom.consultorio.empresa.repositories.EmpresaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Log4j2
@Component
@RequiredArgsConstructor
public class ConsultarEmpresaPorSlugUseCase {

    private final EmpresaRepository empresaRepository;

    /**
     * Consulta uma determinada empresa a partir de seu slug de identificação.
     * @param slug texto único de identificação da empresa
     * @return empresa encontrada, ou {@code Optional.empty()} caso contrário.
     */
    public Optional<EmpresaModel> executar(String slug) {
        return this.empresaRepository.findBySlug(slug);
    }
}
