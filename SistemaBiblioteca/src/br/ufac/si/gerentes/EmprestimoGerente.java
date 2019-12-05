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
		
		public void removerEmprestimo(Emprestimo emprestimo) {
			em.getTransaction().begin();
			em.remove(emprestimo);
			em.getTransaction().commit();

		}
		
		public Emprestimo buscarEmprestimo(long id) {
			return em.find(Emprestimo.class, id);
		}
		
		//consultas JPQL
		
		@SuppressWarnings("unchecked")
		public List<Emprestimo> buscarTodos(){
			return em.createNamedQuery("Emprestimo.todos").getResultList();	
		}
		
		@SuppressWarnings("unchecked")
		public List<Emprestimo> buscarTodosPorNomeContendo(String nome){
			return em
					.createNamedQuery("Emprestimo.todosPorNomeContendo")
					.setParameter("nome", "%"+nome+"%")
					.getResultList();
		}	

		public void encerrar() {
			em.clear();
			emf.close();
		}
}

