package br.com.bom.consultorio.shared.http.context;

import br.com.bom.consultorio.empresa.models.EmpresaModel;

/**
 * Gerenciar variável controlada por thread para armazenar qual é a empresa que está logada no momento.
 *
 * O valor armazenado é o identificador da empresa.
 */
public class EmpresaTenantContext {

    private static final ThreadLocal<EmpresaModel> EMPRESA_ATUAL = new ThreadLocal<>();

    private EmpresaTenantContext() {}

    public static void setEmpresaAtual(EmpresaModel empresaModel) {
        EMPRESA_ATUAL.set(empresaModel);
    }

    public static void limpar() {
        EMPRESA_ATUAL.remove();
    }

    public static EmpresaModel getEmpresaAtual() {
        return EMPRESA_ATUAL.get();
    }
}
