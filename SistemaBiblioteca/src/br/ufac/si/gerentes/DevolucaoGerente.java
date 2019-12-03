package br.ufac.si.gerentes;

import java.util.List;

import javax.persistence.*;

import br.ufac.si.entidades.*;

public class DevolucaoGerente {
	
	private EntityManagerFactory emf;
	private EntityManager em;

	public DevolucaoGerente() {
		emf = Persistence.createEntityManagerFactory("Biblioteca");
		em = emf.createEntityManager();
	}
	
	//Gerenciando Emprestimo

		public void gravar(Devolucao devolucao) {
			em.getTransaction().begin();
			em.persist(devolucao);
			em.getTransaction().commit();
		}
		
		
		//consultas JPQL
		
		@SuppressWarnings("unchecked")
		public List<Devolucao> buscarTodos(){
			return em.createNamedQuery("Devolucao.todos").getResultList();	
		}
		
		@SuppressWarnings("unchecked")
		public List<Devolucao> buscarTodosPorNomeContendo(String nome){
			return em
					.createNamedQuery("Devolucao.todosPorNomeContendo")
					.setParameter("nome", "%"+nome+"%")
					.getResultList();
		}

		public void encerrar() {
			em.clear();
			emf.close();
		}
}

