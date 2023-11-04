package br.com.bom.consultorio.usuarios.repository;

import br.com.bom.consultorio.usuarios.models.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, Integer> {

    boolean existsByEmail(String email);
}
