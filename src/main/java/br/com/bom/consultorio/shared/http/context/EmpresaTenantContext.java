package br.com.bom.consultorio.shared.http.context;

/**
 * Gerenciar variável controlada por thread para armazenar qual é a empresa que está logada no momento.
 *
 * O valor armazenado é o identificador da empresa.
 */
public class EmpresaTenantContext {

    private static final ThreadLocal<String> EMPRESA_ATUAL = new ThreadLocal<>();

    private EmpresaTenantContext() {}

    public static void setEmpresaAtual(String empresaAtual) {
        EMPRESA_ATUAL.set(empresaAtual);
    }

    public static void limpar() {
        EMPRESA_ATUAL.remove();
    }

    public static String getEmpresaAtual() {
        return EMPRESA_ATUAL.get();
    }
}
