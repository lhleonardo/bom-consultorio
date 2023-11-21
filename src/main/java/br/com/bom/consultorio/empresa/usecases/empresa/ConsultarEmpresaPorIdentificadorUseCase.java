package br.com.bom.consultorio.empresa.usecases.empresa;

import br.com.bom.consultorio.empresa.models.empresa.EmpresaModel;
import br.com.bom.consultorio.empresa.repositories.EmpresaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Log4j2
@Component
@RequiredArgsConstructor
public class ConsultarEmpresaPorIdentificadorUseCase {

    private final EmpresaRepository empresaRepository;

    /**
     * Consulta uma determinada empresa a partir de seu identificador UUID
     * @param identificador UUID de identificação da empresa
     * @return empresa encontrada, ou {@code Optional.empty()} caso contrário.
     */
    public Optional<EmpresaModel> executar(String identificador) {
        return this.empresaRepository.findByIdentificador(identificador);
    }
}
