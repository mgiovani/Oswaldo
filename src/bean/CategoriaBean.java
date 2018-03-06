package bean;

import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import entity.Categoria;
import entity.Curso;

import java.io.Serializable;
import java.util.List;

@ManagedBean
@SessionScoped
public class CategoriaBean implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private Categoria categoria;

	public Categoria getCategoria()
	{
		return categoria;
	}

	public void setCategoria(Categoria categoria)
	{
		this.categoria = categoria;
	}

	@Resource
	UserTransaction ut;
	@PersistenceContext
	EntityManager em;
	
	public CategoriaBean()
	{
		this.categoria = new Categoria();
	}
	
	public void salvar()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			ut.begin();
			em.merge(this.categoria);
			ut.commit();
	        context.addMessage(null, new FacesMessage("Sucesso!",  categoria.getNome() + " foi salvo com sucesso."));
			this.categoria = new Categoria();
		} 
		catch (SecurityException | IllegalStateException | NotSupportedException | SystemException | RollbackException
				| HeuristicMixedException | HeuristicRollbackException e) {
			context.addMessage(null, new FacesMessage("Falha!",  categoria.getNome() + " não foi salvo."));
		}
	}
	
	public void selecionar(Categoria c)
	{
		this.setCategoria(c);
	}
	
	public void limpar()
	{
		this.categoria = new Categoria();
	}
	
	public void remover(Categoria c)
	{
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			ut.begin();
			em.remove(em.contains(c) ? c : em.merge(c));
			ut.commit();
			context.addMessage(null, new FacesMessage("Sucesso!",  categoria.getNome() + " foi removido com sucesso."));
			this.categoria = new Categoria();
		} 
		catch (SecurityException | IllegalStateException | NotSupportedException | SystemException | RollbackException
				| HeuristicMixedException | HeuristicRollbackException e) {
			System.out.println("Erro salvar();");
		}
	}
	
	public void addCurso(Curso c)
	{
		this.getCategoria().setCurso(c);
	}
	
	public Categoria buscar(int id)
	{
		Categoria c = em.find(this.categoria.getClass(), id);
		return c;
	}
	
	public List<Categoria> listarTodos()
	{
		return em.createQuery("Select c from Categoria c", Categoria.class).getResultList();
	}
}
