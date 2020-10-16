package br.edu.uepb.springexample;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class AlunoRepository {
	
	private static HashSet<Aluno> alunos = new HashSet<Aluno>();
	
	public List<Aluno> getAll(){
		return new ArrayList<Aluno>(alunos);
	}
	
	public void create(Aluno a) {
		if(a.getId()==0)
			a.setId(genId(alunos.size()+1));
		alunos.add(a);
	}
	
	public boolean containsId(long id) {
		for(Aluno a: alunos)
			if(a.getId()==id)
				return true;
		return false;
	}
	
	public long genId(final long possible) {
		if(this.containsId(possible))
			return genId(possible+1);
		return possible;
	}
	
	public Aluno getById(long id) {
		for(Aluno a: alunos)
			if(a.getId()==id)
				return a;
		return null;
	}
	
	public void edit(Aluno a) {
		alunos.remove(getById(a.getId()));
		alunos.add(a);
	}
	
	public void delete(Aluno a) {
		alunos.remove(a);
	}
}
