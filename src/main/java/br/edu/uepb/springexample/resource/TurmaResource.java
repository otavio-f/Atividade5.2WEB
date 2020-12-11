
package br.edu.uepb.springexample.resource;

import java.util.List;

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

import br.edu.uepb.springexample.model.Turma;
import br.edu.uepb.springexample.repository.TurmaRepository;

// The Java class will be hosted at the URI path "/turma"
@RestController
@RequestMapping("/api/turmas")
public class TurmaResource {
	
	private static TurmaRepository turmaRepository = new TurmaRepository();
    
    @GetMapping
    public Iterable<Turma> getTurmas() {
    	return turmaRepository.getAll();
    }
    
    @PostMapping
    public ResponseEntity<Turma> createTurma(@RequestBody Turma turma) {
    	turmaRepository.create(turma);
    	return new ResponseEntity<Turma>(turma, HttpStatus.CREATED);
    }
    
    @GetMapping("{id}")
    public ResponseEntity<Turma> getTurmaById(@PathVariable("id") long id) {
    	Turma a = turmaRepository.getById(id);
    	if(a == null)
    		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    	return new ResponseEntity<Turma>(a, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Turma> editTurma(@PathVariable("id") long id, @RequestBody Turma turma) {
    	Turma a = turmaRepository.getById(id);
    	if(a == null) {//Guia diz que deve criar caso n exista
    		turmaRepository.create(turma);
    		return new ResponseEntity<Turma>(turma, HttpStatus.CREATED);
    	}
    	try {
    		turma.setId(id);
    		turmaRepository.edit(turma);
    		return new ResponseEntity<Turma>(turma, HttpStatus.OK);
    	} catch(Exception e) {
    		throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    	}
    }
    
    @DeleteMapping("{id}")
    public void deleteTurma(@PathVariable("id") long id) {
    	Turma a = turmaRepository.getById(id);
    	if(a == null)
    		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    	try {
    		turmaRepository.delete(a);
    	} catch(Exception e) {
    		throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    	}
    }
}
