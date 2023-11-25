package br.com.bom.consultorio.empresa.repositories;

import br.com.bom.consultorio.empresa.models.convite.ConviteEmpresaModel;
import br.com.bom.consultorio.empresa.models.convite.StatusConviteEnum;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.time.OffsetDateTime;
import java.util.List;

public interface ConviteEmpresaRepository extends JpaRepository<ConviteEmpresaModel, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<ConviteEmpresaModel> findByStatusAndDataExpiracaoBefore(StatusConviteEnum status, OffsetDateTime dataReferencia);
}
