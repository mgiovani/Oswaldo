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

import entity.Turma;

import java.io.Serializable;
import java.util.List;

@ManagedBean
@SessionScoped
public class TurmaBean implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private Turma turma;

	public Turma getTurma()
	{
		return turma;
	}

	public void setTurma(Turma turma)
	{
		this.turma = turma;
	}

	@Resource
	UserTransaction ut;
	@PersistenceContext
	EntityManager em;
	
	public TurmaBean()
	{
		this.turma = new Turma();
	}
	
	public void salvar()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			ut.begin();
			em.merge(this.turma);
			ut.commit();
	        context.addMessage(null, new FacesMessage("Sucesso!",  "Turma salva com sucesso."));
			this.turma = new Turma();
		} 
		catch (SecurityException | IllegalStateException | NotSupportedException | SystemException | RollbackException
				| HeuristicMixedException | HeuristicRollbackException e) {
			context.addMessage(null, new FacesMessage("Falha!",  "Turma não foi salva."));
		}
	}
	
	public void selecionar(Turma c)
	{
		this.setTurma(c);
	}
	
	public void limpar()
	{
		this.turma = new Turma();
	}
	
	public void remover(Turma c)
	{
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			ut.begin();
			em.remove(em.contains(c) ? c : em.merge(c));
			ut.commit();
			context.addMessage(null, new FacesMessage("Sucesso!",  "Removido com sucesso."));
			this.turma = new Turma();
		} 
		catch (SecurityException | IllegalStateException | NotSupportedException | SystemException | RollbackException
				| HeuristicMixedException | HeuristicRollbackException e) {
			System.out.println("Erro salvar();");
		}
	}
	
	public Turma buscar(int id)
	{
		Turma c = em.find(this.turma.getClass(), id);
		return c;
	}
	
	public List<Turma> listarTodos()
	{
		return em.createQuery("Select c from Turma c", Turma.class).getResultList();
	}
}
