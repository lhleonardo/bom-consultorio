package br.com.bom.consultorio.shared.auth.services;

import br.com.bom.consultorio.usuarios.models.UsuarioModel;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioAutenticadoService {

    /**
     * Retorna os dados do usuário autenticado.
     * @return model do usuário autenticado.
     */
    public UsuarioModel getUsuarioAutenticado() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return (UsuarioModel) authentication.getPrincipal();
    }

    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public void setUsuarioLogado(Authentication authentication) {
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
