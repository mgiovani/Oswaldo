package entity;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;

import javax.persistence.*;

@Entity
public class Curso implements Serializable {

	   
	@Id @GeneratedValue
	private Integer id;
	private String nome;
	private String descricao;
	private Integer duracao;
	private Integer qtdHoras;
	
	private static final long serialVersionUID = 1L;

	public Curso() {
		super();
	}   
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}   
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}   
	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}   
	public Integer getDuracao() {
		return this.duracao;
	}

	public void setDuracao(Integer duracao) {
		this.duracao = duracao;
	}   
	public Integer getQtdHoras() {
		return this.qtdHoras;
	}

	public void setQtdHoras(Integer qtdHoras) {
		this.qtdHoras = qtdHoras;
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
        Curso other = (Curso) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
    @Override
    public String toString() {
        return "Classe [id=" + id + ", nome=" + nome + "]";
    }
}
