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

import entity.Curso;

import java.io.Serializable;
import java.util.List;

@ManagedBean
@SessionScoped
public class CursoBean implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private Curso curso;

	public Curso getCurso()
	{
		return curso;
	}

	public void setCurso(Curso curso)
	{
		this.curso = curso;
	}


	@Resource
	UserTransaction ut;
	@PersistenceContext
	EntityManager em;
	
	public CursoBean()
	{
		this.curso = new Curso();
	}
	
	public void salvar()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			ut.begin();
			em.merge(this.curso);
			ut.commit();
	        context.addMessage(null, new FacesMessage("Sucesso!",  curso.getNome() + " foi salvo com sucesso."));
			this.curso = new Curso();
		} 
		catch (SecurityException | IllegalStateException | NotSupportedException | SystemException | RollbackException
				| HeuristicMixedException | HeuristicRollbackException e) {
			context.addMessage(null, new FacesMessage("Falha!",  curso.getNome() + " não foi salvo."));
		}
	}
	
	public void selecionar(Curso c)
	{
		this.setCurso(c);
	}
	
	public void limpar()
	{
		this.curso = new Curso();
	}
	
	public void remover(Curso c)
	{
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			ut.begin();
			em.remove(em.contains(c) ? c : em.merge(c));
			ut.commit();
			context.addMessage(null, new FacesMessage("Sucesso!",  curso.getNome() + " foi removido com sucesso."));
			this.curso = new Curso();
		} 
		catch (SecurityException | IllegalStateException | NotSupportedException | SystemException | RollbackException
				| HeuristicMixedException | HeuristicRollbackException e) {
			System.out.println("Erro salvar();");
		}
	}
	
	public Curso buscar(int id)
	{
		Curso c = em.find(this.curso.getClass(), id);
//		Query consulta = em.createQuery("SELECT c FROM Curso c WHERE id = :id");
//		consulta.setParameter("id", id);
//		return (Curso)consulta.getSingleResult();

		return c;
	}
	
	public List<Curso> listarTodos()
	{
		return em.createQuery("Select c from Curso c", Curso.class).getResultList();
	}
}
