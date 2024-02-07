package br.com.bom.consultorio.empresa.models.convite;

/**
 * Situações nas quais um convite pode se encontrar. Define todos os estágios do convite.
 */
public enum StatusConviteEnum {

    /**
     * Etapa inicial do convite, indicando que está pendente de aceitação para ingressar em uma determinada empresa.
     */
    PENDENTE,

    /**
     * Tempo máximo de duração do convite foi excedido, impossibilitando seu uso.
     */
    EXPIRADO,

    /**
     * Administrador da empresa/plataforma marcou o convite como cancelado, impossibilitando seu uso
     */
    CANCELADO,

    /**
     * Convite aceito e utilizado com sucesso. Não pode ser utilizado novamente.
     */
    FINALIZADO;
}
