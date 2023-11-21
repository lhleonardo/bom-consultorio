package br.com.bom.consultorio.empresa.usecases.empresa;

import br.com.bom.consultorio.empresa.models.empresa.EmpresaModel;
import br.com.bom.consultorio.empresa.repositories.EmpresaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Log4j2
@Component
@RequiredArgsConstructor
public class BuscarEmpresasUseCase {

    private final EmpresaRepository empresaRepository;

    @Transactional(readOnly = true)
    public List<EmpresaModel> executar() {
        return this.empresaRepository.findAll();
    }
}
