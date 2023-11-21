package br.com.bom.consultorio.empresa.repositories;

import br.com.bom.consultorio.empresa.models.empresa.EmpresaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface EmpresaRepository extends JpaRepository<EmpresaModel, Integer> {

    Optional<EmpresaModel> findBySlug(String slug);
    Optional<EmpresaModel> findByIdentificador(String identificador);

    Boolean existsByCnpj(String cnpj);
    Boolean existsBySlug(String slug);

    @Query("""
            SELECT count(e.id) > 0 FROM EmpresaModel e
            JOIN e.membros m
            JOIN m.usuario u
            WHERE e.id = :idEmpresa AND u.email = :email
        """)
    Boolean existsMembroByEmail(Integer idEmpresa, String email);
}
