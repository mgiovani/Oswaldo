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

import entity.Aluno;

import java.io.Serializable;
import java.util.List;

@ManagedBean
@SessionScoped
public class AlunoBean implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private Aluno aluno;

	public Aluno getAluno()
	{
		return aluno;
	}

	public void setAluno(Aluno aluno)
	{
		this.aluno = aluno;
	}

	@Resource
	UserTransaction ut;
	@PersistenceContext
	EntityManager em;
	
	public AlunoBean()
	{
		this.aluno = new Aluno();
	}
	
	public void salvar()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			ut.begin();
			em.merge(this.aluno);
			ut.commit();
	        context.addMessage(null, new FacesMessage("Sucesso!",  aluno.getNome() + " foi salvo com sucesso."));
			this.aluno = new Aluno();
		} 
		catch (SecurityException | IllegalStateException | NotSupportedException | SystemException | RollbackException
				| HeuristicMixedException | HeuristicRollbackException e) {
			context.addMessage(null, new FacesMessage("Falha!",  aluno.getNome() + " não foi salvo."));
		}
	}
	
	public void selecionar(Aluno c)
	{
		this.setAluno(c);
	}
	
	public void limpar()
	{
		this.aluno = new Aluno();
	}
	
	public void remover(Aluno c)
	{
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			ut.begin();
			em.remove(em.contains(c) ? c : em.merge(c));
			ut.commit();
			context.addMessage(null, new FacesMessage("Sucesso!",  aluno.getNome() + " foi removido com sucesso."));
			this.aluno = new Aluno();
		} 
		catch (SecurityException | IllegalStateException | NotSupportedException | SystemException | RollbackException
				| HeuristicMixedException | HeuristicRollbackException e) {
			System.out.println("Erro salvar();");
		}
	}
	
	public Aluno buscar(int id)
	{
		Aluno c = em.find(this.aluno.getClass(), id);
		return c;
	}
	
	public List<Aluno> listarTodos()
	{
		return em.createQuery("Select c from Aluno c", Aluno.class).getResultList();
	}
}
