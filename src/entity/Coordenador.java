package entity;

import entity.Curso;
import entity.Usuario;
import java.io.Serializable;

import javax.persistence.*;

@Entity
public class Coordenador extends Usuario implements Serializable {
	
	@OneToOne(cascade = {CascadeType.ALL})
	private Curso curso;
	
	public Curso getCurso()
	{
		return curso;
	}

	public void setCurso(Curso curso)
	{
		this.curso = curso;
	}
	
	private static final long serialVersionUID = 1L;

	public Coordenador() {
		super();
	}
}
