package br.edu.uepb.springexample.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.uepb.springexample.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	Usuario findByUsername(String username);
}
