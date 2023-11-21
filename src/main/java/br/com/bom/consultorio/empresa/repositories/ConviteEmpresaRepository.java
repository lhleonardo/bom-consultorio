package br.com.bom.consultorio.empresa.repositories;

import br.com.bom.consultorio.empresa.models.convite.ConviteEmpresaModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConviteEmpresaRepository extends JpaRepository<ConviteEmpresaModel, Long> {
}
