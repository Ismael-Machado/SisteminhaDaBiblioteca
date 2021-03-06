package br.ufac.si.gerentes;

import java.util.List;

import javax.persistence.*;

import br.ufac.si.entidades.Exemplar;
import br.ufac.si.entidades.Livro;

public class LivroGerente {

	private EntityManagerFactory emf;
	private EntityManager em;

	public LivroGerente() {
		emf = Persistence.createEntityManagerFactory("Biblioteca");
		em = emf.createEntityManager();
	}

	//Gerenciando Livros

	public void incluirLivro(Livro livro) {
		em.getTransaction().begin();
		em.persist(livro);
		em.getTransaction().commit();
	}

	public void removerLivro(Livro livro) {
		em.getTransaction().begin();
		em.remove(livro);
		em.getTransaction().commit();

	}

	public void alterarLivro(Livro livro) {
		em.getTransaction().begin();
		em.merge(livro);
		em.getTransaction().commit();
	}

	public Livro buscarLivro(long id) {
		return em.find(Livro.class, id);
	}
	
	//consultas JPQL
	
	@SuppressWarnings("unchecked")
	public List<Livro> buscarTodos(){
		return em.createNamedQuery("Livro.todos").getResultList();	
	}
	
	@SuppressWarnings("unchecked")
	public List<Livro> buscarTodosPorTitulo(){
		return em.createNamedQuery("Livro.todosPorTitulo").getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public Livro buscarTodosPorTituloContendo(String titulo){
		try {
			return (Livro) em
					.createNamedQuery("Livro.todosPorTituloContendo")
					.setParameter("titulo", "%"+titulo+"%")
					.getSingleResult();
		}catch (Exception e) {
			return null;
		}
		
	}	
	
	public List<Livro> buscarTodosPorTituloContendo2(String titulo){
		try {
			return em
					.createNamedQuery("Livro.todosPorTituloContendo")
					.setParameter("titulo", "%"+titulo+"%")
					.getResultList();
		} catch (Exception e) {
			return null;
		}
		
	}	
	
	@SuppressWarnings("unchecked")
	public List<Exemplar> buscarExemplaresDisponivel(Livro livro){
		return em
				.createNamedQuery("Livro.exemplaresDisponivel")
				.setParameter("id", livro.getId())
				.getResultList();
	}	
	
	@SuppressWarnings("unchecked")
	public List<Exemplar> buscarTodosExemplares(Livro livro){
		return em
				.createNamedQuery("Livro.todosExemplares")
				.setParameter("id", livro.getId())
				.getResultList();
	}

	public void encerrar() {
		em.clear();
		emf.close();
	}
}
