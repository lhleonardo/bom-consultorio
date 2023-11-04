package br.com.bom.consultorio.empresa.repositories;

import br.com.bom.consultorio.empresa.models.EmpresaModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmpresaRepository extends JpaRepository<EmpresaModel, Integer> {

    Optional<EmpresaModel> findByCnpj(String cnpj);

    Boolean existsByCnpj(String cnpj);
    Boolean existsBySlug(String slug);
}
