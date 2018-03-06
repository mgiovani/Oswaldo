package entity;

import java.io.Serializable;
import java.lang.Integer;
import java.util.Date;
import java.util.List;
import entity.Curso;
import javax.persistence.*;

@Entity
public class Turma implements Serializable {

	   
	@Id @GeneratedValue
	private Integer id;
	@OneToOne
	private Curso curso;
	@ManyToOne(targetEntity=Aluno.class)
	private List<Aluno> alunos;	
	@Temporal(TemporalType.DATE)
	private Date dtInicio;
	private static final long serialVersionUID = 1L;
	
	
	public Date getDtInicio()
	{
		return dtInicio;
	}
	public void setDtInicio(Date dtInicio)
	{
		this.dtInicio = dtInicio;
	}
	public Integer getId()
	{
		return id;
	}
	public void setId(Integer id)
	{
		this.id = id;
	}
	public Curso getCurso()
	{
		return curso;
	}
	public void setCurso(Curso curso)
	{
		this.curso = curso;
	}
	public List<Aluno> getAlunos()
	{
		return alunos;
	}
	public void setAlunos(List<Aluno> alunos)
	{
		this.alunos = alunos;
	}
	
	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Turma other = (Turma) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
    @Override
    public String toString() {
        return "Categoria [id=" + id + "]";
    }
}
