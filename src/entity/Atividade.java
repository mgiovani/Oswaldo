package entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Atividade implements Serializable {
	
	@Id @GeneratedValue
	private Integer id;
	@OneToOne
	private Categoria categoria;
	private String descricao;
	private Integer horas;
	@Temporal(TemporalType.DATE)
	private Date dtInicio;
	@Temporal(TemporalType.DATE)
	private Date dtTermino;
	private static final long serialVersionUID = 1L;
	
	
	public Integer getId()
	{
		return id;
	}
	public void setId(Integer id)
	{
		this.id = id;
	}
	public Categoria getCategoria()
	{
		return categoria;
	}
	public void setCategoria(Categoria categoria)
	{
		this.categoria = categoria;
	}
	public String getDescricao()
	{
		return descricao;
	}
	public void setDescricao(String descricao)
	{
		this.descricao = descricao;
	}
	public Integer getHoras()
	{
		return horas;
	}
	public void setHoras(Integer horas)
	{
		this.horas = horas;
	}
	public Date getDtInicio()
	{
		return dtInicio;
	}
	public void setDtInicio(Date dtInicio)
	{
		this.dtInicio = dtInicio;
	}
	public Date getDtTermino()
	{
		return dtTermino;
	}
	public void setDtTermino(Date dtTermino)
	{
		this.dtTermino = dtTermino;
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
        Atividade other = (Atividade) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
    @Override
    public String toString() {
        return "Atividade [id=" + id + "]";
    }
}
