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

import entity.Atividade;
import entity.Categoria;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ManagedBean
@SessionScoped
public class AtividadeBean implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private Atividade atividade;

	public Atividade getAtividade()
	{
		return atividade;
	}

	public void setAtividade(Atividade atividade)
	{
		this.atividade = atividade;
	}

	@Resource
	UserTransaction ut;
	@PersistenceContext
	EntityManager em;
	
	public AtividadeBean()
	{
		this.atividade = new Atividade();
	}
	
	public void salvar()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			ut.begin();
			em.merge(this.atividade);
			ut.commit();
	        context.addMessage(null, new FacesMessage("Sucesso!",  atividade.getDescricao() + " foi salvo com sucesso."));
			this.atividade = new Atividade();
		} 
		catch (SecurityException | IllegalStateException | NotSupportedException | SystemException | RollbackException
				| HeuristicMixedException | HeuristicRollbackException e) {
			context.addMessage(null, new FacesMessage("Falha!",  atividade.getDescricao() + " não foi salvo."));
		}
	}
	
	public void selecionar(Atividade c)
	{
		this.setAtividade(c);
	}
	
	public void limpar()
	{
		this.atividade = new Atividade();
	}
	
	public void remover(Atividade c)
	{
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			ut.begin();
			em.remove(em.contains(c) ? c : em.merge(c));
			ut.commit();
			context.addMessage(null, new FacesMessage("Sucesso!",  "Removido com sucesso."));
			this.atividade = new Atividade();
		} 
		catch (SecurityException | IllegalStateException | NotSupportedException | SystemException | RollbackException
				| HeuristicMixedException | HeuristicRollbackException e) {
			System.out.println("Erro salvar();");
		}
	}
	
	public Atividade buscar(int id)
	{
		Atividade c = em.find(this.atividade.getClass(), id);
		return c;
	}
	
	public List<Atividade> listarTodos()
	{
		return em.createQuery("Select c from Atividade c", Atividade.class).getResultList();
	}
	
	public Integer somar(List<Categoria> cat)
	{
		Integer sum = 0;
		Map<Integer,Integer> mapa = new HashMap<Integer,Integer>();
		List<Atividade> atv = listarTodos();		
		for (Categoria categoria : cat) {
			mapa.put(categoria.getId(), categoria.getQtdMaxHoras());
		}
		for (Atividade atividade : atv)
		{
			if((mapa.get(atividade.getCategoria().getId()) - atividade.getHoras()) > 0) {
				mapa.put(atividade.getCategoria().getId(), mapa.get(atividade.getCategoria().getId())-atividade.getHoras());
				sum += atividade.getHoras();}
			else {
				sum += mapa.get(atividade.getCategoria().getId());
				mapa.put(atividade.getCategoria().getId(), 0);
		}
		}
		return sum;
		}
	}