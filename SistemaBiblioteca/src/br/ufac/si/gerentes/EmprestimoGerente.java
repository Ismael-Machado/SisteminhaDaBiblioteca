package br.ufac.si.gerentes;

import java.util.List;

import javax.persistence.*;

import br.ufac.si.entidades.*;

public class EmprestimoGerente {
	
	private EntityManagerFactory emf;
	private EntityManager em;

	public EmprestimoGerente() {
		emf = Persistence.createEntityManagerFactory("Biblioteca");
		em = emf.createEntityManager();
	}
	
	//Gerenciando Emprestimo

		public void fazerEmprestimo(Emprestimo emprestimo) {
			em.getTransaction().begin();
			em.persist(emprestimo);
			em.getTransaction().commit();
		}
		
		public long removerEmprestimo(long id) {
			Emprestimo e = em.find(Emprestimo.class, id);
			long emp = e.getId();

			em.getTransaction().begin();
			em.remove(e);
			em.getTransaction().commit();

			return emp;
		}
		
		//consultas JPQL
		
		@SuppressWarnings("unchecked")
		public List<Emprestimo> buscarTodos(){
			return em.createNamedQuery("Emprestimo.todos").getResultList();	
		}
		@SuppressWarnings("unchecked")
		public List<Emprestimo> buscarTodosPorHorario(){
			return em.createNamedQuery("Emprestimo.todosPorHorario").getResultList();
		}
		@SuppressWarnings("unchecked")
		public List<Emprestimo> buscarTodosPorNomeContendo(String termo){
			return em
					.createNamedQuery("Emprestimo.todosPorNomeContendo")
					.setParameter("termo", "%"+termo+"%")
					.getResultList();
		}	

		public void encerrar() {
			em.clear();
			emf.close();
		}
}

