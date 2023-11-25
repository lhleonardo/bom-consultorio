package br.com.bom.consultorio.empresa.repositories;

import br.com.bom.consultorio.empresa.models.convite.ConviteEmpresaModel;
import br.com.bom.consultorio.empresa.models.convite.StatusConviteEnum;
import br.com.bom.consultorio.empresa.models.empresa.EmpresaModel;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

public interface ConviteEmpresaRepository extends JpaRepository<ConviteEmpresaModel, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<ConviteEmpresaModel> findByStatusAndDataExpiracaoBefore(StatusConviteEnum status, OffsetDateTime dataReferencia);

    @EntityGraph(attributePaths = {"empresa", "criadoPor"})
    Optional<ConviteEmpresaModel> findByEmailAndStatusAndEmpresa(String email, StatusConviteEnum statusConviteEnum, EmpresaModel empresa);

    @EntityGraph(attributePaths = {"empresa", "criadoPor"})
    Optional<ConviteEmpresaModel> findByCodigoAndEmpresa(String codigo, EmpresaModel empresa);
}
