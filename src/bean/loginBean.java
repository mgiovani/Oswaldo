package bean;

import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

import entity.Usuario;

import java.io.IOException;
import java.io.Serializable;

@ManagedBean
@SessionScoped
public class loginBean implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private Usuario usuario;

	public Usuario getUsario()
	{
		return usuario;
	}

	public void setUsario(Usuario usuario)
	{
		this.usuario = usuario;
	}

	@Resource
	UserTransaction ut;
	@PersistenceContext
	EntityManager em;
	
	public loginBean()
	{
		this.usuario = new Usuario();
	}
	
	public void login() throws IOException
	{
		FacesContext context = FacesContext.getCurrentInstance();
		
		String string = "SELECT u FROM Coordenador u WHERE u.login=:login and u.senha=:senha";
		String string2 = "SELECT u FROM Aluno u WHERE u.login=:login and u.senha=:senha";
		
		Query consulta = em.createQuery(string);
		Query consulta2 = em.createQuery(string2);
		
		consulta.setParameter("login", this.usuario.getLogin());
		consulta.setParameter("senha", this.usuario.getSenha());
		consulta2.setParameter("login", this.usuario.getLogin());
		consulta2.setParameter("senha", this.usuario.getSenha());
		
		try
		{		
			Usuario u = (Usuario) consulta.getSingleResult();
			if(u!=null) FacesContext.getCurrentInstance().getExternalContext().redirect("coordenador.xhtml");
		}
		catch (NoResultException nre)
		{
		}
		try 
		{
			Usuario u2 = (Usuario) consulta2.getSingleResult();
			if(u2!=null) FacesContext.getCurrentInstance().getExternalContext().redirect("aluno.xhtml");
		}
		catch (NoResultException nre)
		{
		}
		context.addMessage(null, new FacesMessage("Falha!", " Credenciais inválidas."));	
	}	
	
	public void logout() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
    }
}
