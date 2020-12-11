package br.edu.uepb.springexample.repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import br.edu.uepb.springexample.model.Turma;

public class TurmaRepository {
	
	private static HashSet<Turma> turmas = new HashSet<Turma>();
	
	public List<Turma> getAll(){
		return new ArrayList<Turma>(turmas);
	}
	
	public void create(Turma t) {
		if(t.getId()==0)
			t.setId(genId(turmas.size()+1));
		turmas.add(t);
	}
	
	public boolean containsId(long id) {
		for(Turma a: turmas)
			if(a.getId()==id)
				return true;
		return false;
	}
	
	public long genId(final long possible) {
		if(this.containsId(possible))
			return genId(possible+1);
		return possible;
	}
	
	public Turma getById(long id) {
		for(Turma a: turmas)
			if(a.getId()==id)
				return a;
		return null;
	}
	
	public void edit(Turma t) {
		turmas.remove(getById(t.getId()));
		turmas.add(t);
	}
	
	public void delete(Turma t) {
		turmas.remove(t);
	}
}
