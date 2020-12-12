package br.edu.uepb.springexample.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.uepb.springexample.model.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
	//automágica!
}
