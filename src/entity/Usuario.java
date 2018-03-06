package entity;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import javax.persistence.*;

@MappedSuperclass
public class Usuario implements Serializable {

	   
	@Id @GeneratedValue
	private Integer id;
	private String login;
	private String senha;
	private String nome;
	private static final long serialVersionUID = 1L;

	public Usuario() {
		super();
	}   
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}   
	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}   
	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}   
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
   
}
