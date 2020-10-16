
package br.edu.uepb.springexample;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

// Hosted at the URI path "/api/alunos"
@RestController
@RequestMapping("/api/alunos")
public class AlunoResource {
	
	private static AlunoRepository alunoRepository = new AlunoRepository();
    
    @GetMapping
    public Iterable<Aluno> getAlunos() {
    	return alunoRepository.getAll();
    }
    
    @PostMapping
    public ResponseEntity<Aluno> createAluno(@RequestBody Aluno aluno) {
    	alunoRepository.create(aluno);
    	return new ResponseEntity<Aluno>(aluno, HttpStatus.CREATED);
    }
    
    @GetMapping("{id}")
    public ResponseEntity<Aluno> getAlunoById(@PathVariable("id") long id) {
    	Aluno a = alunoRepository.getById(id);
    	if(a == null)
    		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    	return new ResponseEntity<Aluno>(a, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Aluno> editAluno(@PathVariable("id") long id, @RequestBody Aluno aluno) {
    	Aluno a = alunoRepository.getById(id);
    	if(a == null) {//Guia diz que deve criar caso n exista
    		alunoRepository.create(aluno);
    		return new ResponseEntity<Aluno>(aluno, HttpStatus.CREATED);
    	}
    	try {
    		aluno.setId(id);
    		alunoRepository.edit(aluno);
    		return new ResponseEntity<Aluno>(aluno, HttpStatus.OK);
    	} catch(Exception e) {
    		throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    	}
    }
    
    @DeleteMapping("{id}")
    public void deleteAluno(@PathVariable("id") long id) {
    	Aluno a = alunoRepository.getById(id);
    	if(a == null)
    		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    	try {
    		alunoRepository.delete(a);
    	} catch(Exception e) {
    		throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    	}
    }
}
