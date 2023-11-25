package br.com.bom.consultorio.empresa.enums;

import br.com.bom.consultorio.shared.http.handlers.exceptions.MensagensExceptionStrategy;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum EmpresaExceptionEnum implements MensagensExceptionStrategy {
    EMPRESA_JA_CADASTRADA("EMP001", "Empresa já cadastrada para o CNPJ informado"),
    SLUG_EMPRESA_JA_CADASTRADO("EMP002", "O 'slug' informado já existe na plataforma"),
    USUARIO_JA_EH_MEMBRO_EMPRESA("EMP003", "O usuário informado já é membro da empresa."),
    CONVITE_EMPRESA_INVALIDA("EMP004", "A empresa para criar o convite é obrigatória"),
    IDENTIFICADOR_EMPRESA_INVALIDO("EMP005", "O identificador da empresa informado é inválido"),
    IDENTIFICADOR_EMPRESA_NAO_INFORMADO("EMP006", "O identificador da empresa não foi informado"),
    DUPLICIDADE_CONVITE("EMP007", "Já existe convite ativo para o destinatário informado"),
    RESTRICAO_CANCELAMENTO_CONVITE("EMP008", "Convite não pode ser cancelado"),
    RESTRICAO_EXPIRACAO_CONVITE("EMP009", "Convite não pode ser expirado"),
    CONVITE_NAO_ENCONTRADO("EMP010", "Convite não encontrado");

    private final String codigo;
    private final String mensagem;

    @Override
    public String getCodigo() {
        return this.codigo;
    }

    @Override
    public String getConteudo() {
        return this.mensagem;
    }
}
