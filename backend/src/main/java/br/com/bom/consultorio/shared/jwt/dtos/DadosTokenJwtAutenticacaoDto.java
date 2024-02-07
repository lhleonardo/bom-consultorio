package br.com.bom.consultorio.shared.jwt.dtos;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class DadosTokenJwtAutenticacaoDto {
    private String identificadorUsuarioAutenticado;
    private boolean usuarioMaster;
    private LocalDateTime dataExpiracao;
}
