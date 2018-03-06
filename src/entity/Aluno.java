package entity;

import java.io.Serializable;
import java.util.List;

import entity.Turma;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Aluno extends Usuario implements Serializable {
	
	@OneToOne
	private Turma turma;
	@OneToMany
	private List<Atividade> atividades;
	private Integer dtTerminoCurso;
	
	private static final long serialVersionUID = 1L;
	
	public Turma getTurma()
	{
		return turma;
	}
	public void setTurma(Turma turma)
	{
		this.turma = turma;
	}
	public List<Atividade> getAtividades()
	{
		return atividades;
	}
	public void setAtividades(List<Atividade> atividades)
	{
		this.atividades = atividades;
	}
	public Integer getDtTerminoCurso()
	{
		return dtTerminoCurso;
	}
	public void setDtTerminoCurso(Integer dtTerminoCurso)
	{
		this.dtTerminoCurso = dtTerminoCurso;
	}	
}
